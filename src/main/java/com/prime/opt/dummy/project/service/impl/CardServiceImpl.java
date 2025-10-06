package com.prime.opt.dummy.project.service.impl;

import com.prime.opt.dummy.project.Enum.card_enum.CardType;
import com.prime.opt.dummy.project.Enum.request_enum.LibRequestType;
import com.prime.opt.dummy.project.Enum.request_enum.RequestStat;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import com.prime.opt.dummy.project.constants.LibrarySystemErrorCodes;
import com.prime.opt.dummy.project.entity.CardEntity;
import com.prime.opt.dummy.project.entity.LibRequestEntity;
import com.prime.opt.dummy.project.model.BorrowBookDetails;
import com.prime.opt.dummy.project.model.CustomBookDetails;
import com.prime.opt.dummy.project.model.CustomUserDetails;
import com.prime.opt.dummy.project.model.IssuedBookDetails;
import com.prime.opt.dummy.project.repository.BookRepository;
import com.prime.opt.dummy.project.repository.CardRepository;
import com.prime.opt.dummy.project.repository.RequestRepository;
import com.prime.opt.dummy.project.repository.UserRepository;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.request.BorrowBooksRequest;
import com.prime.opt.dummy.project.response.BorrowBooksResponse;
import com.prime.opt.dummy.project.response.NewCardResponse;
import com.prime.opt.dummy.project.service.CardService;
import com.prime.opt.dummy.project.service.CustomIdGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final UserRepository userRepository;

    private final CustomIdGeneratorService idGeneratorService;

    private final BookRepository bookRepository;

    private final RequestRepository requestRepository;

    @Override
    public BaseResponse<NewCardResponse> applyForNewCard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        NewCardResponse response = cardRepository.fetchCardDetailsByUserId(userId);

        if(response!=null){
            return new BaseResponse<>(LibrarySystemErrorCodes.ok_code, "Your already having card", response);
        }

        CustomUserDetails customUserDetails = userRepository.fetchUserDetailsByUserId(userId);

        if(customUserDetails !=null){

            CardType cardType = switch (customUserDetails.getRoles()){
                case LIBRARIAN -> null;
                case STAFF -> CardType.STFCARD;
                case STUDENT -> CardType.STUCARD;
            };

            String cardId = idGeneratorService.generateCustomCardId(cardType);

            CardEntity cardEntity = new CardEntity();
            cardEntity.setCardId(cardId);
            cardEntity.setCardType(cardType);
            cardEntity.setIssuedDate(LocalDate.now());
            cardEntity.setFine(false);
            cardEntity.setName(customUserDetails.getName());
            cardEntity.setValidDate(cardEntity.getIssuedDate().plusYears(3));
            cardEntity.setUserId(userId);
            cardEntity.setIssuedBookDetailsList(new ArrayList<>());

            CardEntity entity = cardRepository.save(cardEntity);

            response = getNewCardResponse(entity);
        }
        else{
            return new BaseResponse<>(404, "No user found", null);
        }

        return new BaseResponse<>(LibrarySystemErrorCodes.ok_code, LibrarySystemErrorCodes.ok_msg, response);
    }

    @Override
    @Transactional
    public BaseResponse<BorrowBooksResponse> placeBorrowBookRequest(BorrowBooksRequest borrowBooksRequest) {
        String cardId = borrowBooksRequest.getCardId();
        List<String> bookIds = borrowBooksRequest.getBookIds();

        // check does user having any on its card
        boolean isHavingFine = cardRepository.isHavingFineOnCard(cardId);

        if (!isHavingFine) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();
            CustomUserDetails userDetails = userRepository.fetchUserDetailsByUserId(userId);
            CardEntity cardEntity = cardRepository.findByCardId(cardId);

            if(cardEntity.getIssuedBookDetailsList().size()==5){
                return new BaseResponse<>(LibrarySystemErrorCodes.fail_code, "Your borrowed 5 books you can't borrow more", null);
            }

            Roles userRole = userDetails.getRoles();
            List<BorrowBookDetails> booksList = new ArrayList<>();

            // fetch book details by bookId
            for (String bookId : bookIds) {
                try {
                    CustomBookDetails bookDetails = bookRepository.fetchBookDetailsById(bookId);
                    if (bookDetails != null) {
                        BorrowBookDetails borrowBookDetails = customizeBookDetails(bookDetails);
                        if (userRole == Roles.STUDENT) {
                            borrowBookDetails.setStatus(RequestStat.NOT_AVAILABLE);
                        }
                        else if (cardEntity.getIssuedBookDetailsList().size()==5) {
                            borrowBookDetails.setStatus(RequestStat.REJECTED);
                        }
                        else {
                            //
                            try {
                               // bookRepository.updateBookQtyById(bookId);
                                borrowBookDetails.setStatus(RequestStat.ISSUED);
                                IssuedBookDetails issuedBookDetails = getIssuedBookDetails(bookId, borrowBookDetails);
                                try {
                                    cardEntity.getIssuedBookDetailsList().add(issuedBookDetails);
                                    cardRepository.save(cardEntity);
                                } catch (Exception e) {
                                    // log error while saving card entity;
                                }

                            } catch (Exception ex) {
                                // log error while update qty of book
                            }
                        }
                        booksList.add(borrowBookDetails);
                    }

                } catch (Exception ex) {
                    // log error while fetching data from DB
                }
            }

            String requestId = idGeneratorService.generateCustomRequestId(LibRequestType.BOOK_BORROWING_REQUEST);
            BorrowBooksResponse response = getBorrowBooksResponse(requestId, cardId, userDetails, booksList);
            LibRequestEntity requestEntity = getLibRequestEntity(requestId);
            try {
                requestRepository.save(requestEntity);
            } catch (Exception e) {
                //log error while saving request entity;
            }
            return new BaseResponse<>(LibrarySystemErrorCodes.ok_code, LibrarySystemErrorCodes.ok_msg, response);
        }
        else {
            return new BaseResponse<>(LibrarySystemErrorCodes.fail_code, "Your borrowing books request has been denied due to pending fine", null);
        }
    }



    @Override
    public BaseResponse<List<IssuedBookDetails>> getIssuedBookDetails(String cardId) {
        try{
            CardEntity cardEntity = cardRepository.findByCardId(cardId);
            return new BaseResponse<>(LibrarySystemErrorCodes.ok_code, LibrarySystemErrorCodes.ok_msg, cardEntity.getIssuedBookDetailsList());
        } catch(Exception ex){
            // log error while fetch issued book details
            return new BaseResponse<>(LibrarySystemErrorCodes.fail_code, LibrarySystemErrorCodes.fail_msg, null);
        }
    }

    private static LibRequestEntity getLibRequestEntity(String requestId) {
        LibRequestEntity requestEntity = LibRequestEntity.builder()
                .requestId(requestId)
                .requestType(LibRequestType.BOOK_BORROWING_REQUEST)
                .requestedDate(LocalDate.now())
                .resolvedDate(LocalDate.now())
                .processedBy("system")
                .build();
        return requestEntity;
    }

    private static BorrowBooksResponse getBorrowBooksResponse(String requestId, String cardId, CustomUserDetails userDetails, List<BorrowBookDetails> booksList) {
        BorrowBooksResponse response = BorrowBooksResponse.builder()
                .requestId(requestId)
                .cardId(cardId)
                .name(userDetails.getName())
                .bookDetailsList(booksList)
                .build();
        return response;
    }

    private static IssuedBookDetails getIssuedBookDetails(String bookId, BorrowBookDetails borrowBookDetails) {
        IssuedBookDetails issuedBookDetails = new IssuedBookDetails();
        issuedBookDetails.setBookId(bookId);
        issuedBookDetails.setName(borrowBookDetails.getBookName());
        issuedBookDetails.setIssuedDate(LocalDate.now());
        issuedBookDetails.setIssuedDate(LocalDate.now().plusDays(7));
        return issuedBookDetails;
    }

    private static NewCardResponse getNewCardResponse(CardEntity entity) {
        NewCardResponse response;
        response = new NewCardResponse();

        response.setCardId(entity.getCardId());
        response.setName(entity.getName());
        response.setCardType(entity.getCardType());
        response.setIssuedDate(entity.getIssuedDate());
        response.setValidDate(entity.getValidDate());
        return response;
    }

    private static BorrowBookDetails customizeBookDetails(CustomBookDetails bookDetails){
        return BorrowBookDetails.builder()
                .author(bookDetails.getAuthor())
                .bookId(bookDetails.getBookId())
                .bookName(bookDetails.getBookName())
                .edition(bookDetails.getEdition())
                .language(bookDetails.getLanguage())
                .build();
    }

}
