package com.prime.opt.dummy.project.request;

import com.prime.opt.dummy.project.Enum.book_enum.Genre;
import com.prime.opt.dummy.project.Enum.book_enum.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    private String name;
    private String author;
    private String publisher;
    private String edition;
    private Genre genre;
    private Language language;
    private int totalCopies;
}
