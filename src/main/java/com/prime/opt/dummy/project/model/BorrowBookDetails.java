package com.prime.opt.dummy.project.model;

import com.prime.opt.dummy.project.Enum.book_enum.Language;
import com.prime.opt.dummy.project.Enum.request_enum.RequestStat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BorrowBookDetails {
    private String bookId;
    private String bookName;
    private String author;
    private String edition;
    private Language language;
    private RequestStat status;
}
