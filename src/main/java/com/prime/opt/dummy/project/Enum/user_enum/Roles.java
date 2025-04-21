package com.prime.opt.dummy.project.Enum.user_enum;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Roles {
    STUDENT,
    LIBRARIAN,
    STAFF;

    public String getUserPrefix(Roles roles){
        return switch (roles){
            case STAFF -> "STF";
            case STUDENT -> "STU";
            case LIBRARIAN -> "LIB";
        };
    }
}
