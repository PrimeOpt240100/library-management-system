package com.prime.opt.dummy.project.service.impl;

import com.prime.opt.dummy.project.Enum.card_enum.CardType;
import com.prime.opt.dummy.project.constants.LibrarySystemErrorCodes;
import com.prime.opt.dummy.project.entity.CardEntity;
import com.prime.opt.dummy.project.model.BookDetails;
import com.prime.opt.dummy.project.model.UserDetails;
import com.prime.opt.dummy.project.repository.CardRepository;
import com.prime.opt.dummy.project.repository.UserRepository;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.request.NewCardRequest;
import com.prime.opt.dummy.project.response.NewCardResponse;
import com.prime.opt.dummy.project.service.CardService;
import com.prime.opt.dummy.project.service.CustomIdGeneratorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

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
    public BaseResponse<NewCardResponse> applyForNewCard(NewCardRequest newCardRequest) {
        NewCardResponse response;

        UserDetails userDetails = userRepository.fetchUserDetailsByUserId(newCardRequest.getUserId());

        if(userDetails!=null){

            CardType cardType = switch (userDetails.getRoles()){
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
            cardEntity.setName(userDetails.getName());
            cardEntity.setValidDate(cardEntity.getIssuedDate().plusYears(3));
            cardEntity.setUserId(newCardRequest.getUserId());

            CardEntity entity = cardRepository.save(cardEntity);

            response = new NewCardResponse();

            response.setCardId(entity.getCardId());
            response.setName(entity.getName());
            response.setCardType(entity.getCardType());
            response.setIssuedDate(entity.getIssuedDate());
            response.setValidDate(entity.getValidDate());
        }
        else{
            return new BaseResponse<>(404, "No user found", null);
        }

        return new BaseResponse<>(LibrarySystemErrorCodes.ok_code, LibrarySystemErrorCodes.ok_msg, response);
    }

}
