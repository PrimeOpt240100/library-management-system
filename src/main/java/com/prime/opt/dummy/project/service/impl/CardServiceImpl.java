package com.prime.opt.dummy.project.service.impl;

import com.prime.opt.dummy.project.Enum.card_enum.CardType;
import com.prime.opt.dummy.project.constants.LibrarySystemErrorCodes;
import com.prime.opt.dummy.project.entity.CardEntity;
import com.prime.opt.dummy.project.model.CustomUserDetails;
import com.prime.opt.dummy.project.repository.CardRepository;
import com.prime.opt.dummy.project.repository.UserRepository;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.NewCardResponse;
import com.prime.opt.dummy.project.service.CardService;
import com.prime.opt.dummy.project.service.CustomIdGeneratorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final UserRepository userRepository;

    private final CustomIdGeneratorService idGeneratorService;

    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository, CustomIdGeneratorService customIdGeneratorService){
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.idGeneratorService = customIdGeneratorService;
    }

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

            CardEntity entity = cardRepository.save(cardEntity);

            response = getNewCardResponse(entity);
        }
        else{
            return new BaseResponse<>(404, "No user found", null);
        }

        return new BaseResponse<>(LibrarySystemErrorCodes.ok_code, LibrarySystemErrorCodes.ok_msg, response);
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

}
