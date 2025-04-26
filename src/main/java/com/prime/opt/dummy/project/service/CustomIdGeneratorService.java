package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.Enum.book_enum.Genre;
import com.prime.opt.dummy.project.Enum.card_enum.CardType;
import com.prime.opt.dummy.project.Enum.user_enum.Roles;

public interface CustomIdGeneratorService {

    String generateCustomUserId(Roles roles);

    String generateCustomBookId(Genre genre);

    String generateCustomCardId(CardType cardType);

}
