package com.prime.opt.dummy.project.Enum.request_enum;

import lombok.Getter;

@Getter
public enum LibRequestType {
    BOOK_BORROWING_REQUEST,
    BOOK_RETURNING_REQUEST,
    FINE_SUBMITTING_REQUEST,
    FINE_CREATING_REQUEST,
    CARD_CANCELLING_REQUEST,
    LOST_CARD_REQUEST;

    public String getRequestPrefix(LibRequestType libRequestType){
        return switch (libRequestType){
            case BOOK_BORROWING_REQUEST -> "BBR";
            case BOOK_RETURNING_REQUEST -> "BRR";
            case FINE_SUBMITTING_REQUEST -> "FSR";
            case FINE_CREATING_REQUEST -> "FCR";
            case CARD_CANCELLING_REQUEST -> "CCR";
            case LOST_CARD_REQUEST -> "LCR";
        };
    }
}
