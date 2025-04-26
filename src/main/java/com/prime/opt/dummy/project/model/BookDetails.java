package com.prime.opt.dummy.project.model;

import com.prime.opt.dummy.project.Enum.book_enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDetails {
    private String bookId;
    private String name;
    private Genre genre;
}
