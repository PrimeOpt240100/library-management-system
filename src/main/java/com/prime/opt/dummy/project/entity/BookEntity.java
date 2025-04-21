package com.prime.opt.dummy.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.opt.dummy.project.Enum.book_enum.Genre;
import com.prime.opt.dummy.project.Enum.book_enum.Language;
import com.prime.opt.dummy.project.Enum.book_enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "book_details_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

    @Id
    @Column(name = "book_id")
    private String bookId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "edition")
    private String edition;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_language", nullable = false)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "total_qty", nullable = false)
    private int totalCopies;

    @Column(name = "available_qty", nullable = false)
    private int availableCopies;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "added_date")
    private LocalDate bookAdded;

}
