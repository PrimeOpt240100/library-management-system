package com.prime.opt.dummy.project.Enum.card_enum;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CardType {
    STUCARD,
    STFCARD;

    public String getCardPrefix(CardType cardType){
        return switch (cardType){
            case STFCARD -> "STFC";
            case STUCARD -> "STUC";
        };
    }
}
