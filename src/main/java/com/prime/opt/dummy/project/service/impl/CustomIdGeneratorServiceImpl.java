package com.prime.opt.dummy.project.service.impl;

import com.prime.opt.dummy.project.Enum.book_enum.Genre;
import com.prime.opt.dummy.project.Enum.card_enum.CardType;
import com.prime.opt.dummy.project.Enum.request_enum.LibRequestType;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;
import com.prime.opt.dummy.project.repository.BookRepository;
import com.prime.opt.dummy.project.repository.CardRepository;
import com.prime.opt.dummy.project.repository.RequestRepository;
import com.prime.opt.dummy.project.repository.UserRepository;
import com.prime.opt.dummy.project.service.CustomIdGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomIdGeneratorServiceImpl implements CustomIdGeneratorService {

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final CardRepository cardRepository;

    private final RequestRepository requestRepository;

    @Override
    public String generateCustomUserId(Roles roles) {
        String newCustomId;
        String prefix = roles.getUserPrefix(roles);
        Integer currentId = userRepository.findMaxIdWithPrefix(prefix);

        if(currentId!=null){
            newCustomId = prefix + (currentId+1);
        }
        else{
            newCustomId = prefix + 0;
        }

        return newCustomId;
    }

    @Override
    public String generateCustomBookId(Genre genre) {
        String newCustomId;
        String prefix = genre.getBookPrefix(genre);
        Integer currentId = bookRepository.findMaxIdWithPrefix(prefix);

        if(currentId!=null){
            newCustomId = prefix + (currentId+1);
        }
        else{
            newCustomId = prefix + 0;
        }

        return newCustomId;
    }

    @Override
    public String generateCustomCardId(CardType cardType) {
        String newCustomId;
        String prefix = cardType.getCardPrefix(cardType);
        Integer currentId = cardRepository.findMaxIdWithPrefix(prefix);

        if(currentId!=null){
            newCustomId = prefix + (currentId+1);
        }
        else{
            newCustomId = prefix + 0;
        }
        return newCustomId;
    }

    @Override
    public String generateCustomRequestId(LibRequestType requestType) {
        String newCustomId;
        String prefix = requestType.getRequestPrefix(requestType);
        Integer currentId = requestRepository.findMaxIdWithPrefix(prefix);

        if(currentId!=null){
            newCustomId = prefix + (currentId+1);
        }
        else{
            newCustomId = prefix + 0;
        }
        return newCustomId;
    }

}
