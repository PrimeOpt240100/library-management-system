package com.prime.opt.dummy.project.model;

import com.prime.opt.dummy.project.Enum.book_enum.Language;
import com.prime.opt.dummy.project.Enum.book_enum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CustomBookDetails {
    private String bookId;
    private String bookName;
    private String author;
    private String edition;
    private Language language;
    private Status status;

    public CustomBookDetails(String bookId, String bookName, String author, String edition, Language language, Status status) {
        this.status = status;
        this.language = language;
        this.edition = edition;
        this.author = author;
        this.bookName = bookName;
        this.bookId = bookId;
    }
}
