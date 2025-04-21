package com.prime.opt.dummy.project.Enum.book_enum;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Genre {
    FICTION,
    SCIENCE,
    ENGINEERING,
    FANTASY,
    ROMANCE,
    NA,
    HISTORY;

    public String getBookPrefix(Genre genre){
        return switch (genre){
            case FICTION -> "FIC";
            case SCIENCE -> "SCI";
            case ENGINEERING -> "ENG";
            case FANTASY -> "FNT";
            case ROMANCE -> "ROM";
            case HISTORY -> "HIS";
            case NA -> "NTK";
        };
    }
}
