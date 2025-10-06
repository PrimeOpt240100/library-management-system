package com.prime.opt.dummy.project.request;

import com.prime.opt.dummy.project.Enum.book_enum.Genre;
import com.prime.opt.dummy.project.Enum.book_enum.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBookEntryRequest {

    @NotBlank(message = "Book Name can't be blank or null")
    private String name;

    @NotBlank(message = "Book Name can't be blank or null")
    private String author;

    private String publisher;
    private String edition;
    private Genre genre;
    private Language language;

    @Positive
    private int qty;

}
