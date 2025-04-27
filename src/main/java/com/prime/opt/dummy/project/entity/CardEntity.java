package com.prime.opt.dummy.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.opt.dummy.project.Enum.card_enum.CardType;
import com.prime.opt.dummy.project.model.IssuedBookDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "card_details_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {

    @Id
    @Column(name = "card_id", length = 10)
    private String cardId;

    @Column(name = "name",nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false)
    private CardType cardType;

    @Transient
    private List<IssuedBookDetails> issuedBookDetailsList;

    @Column(name = "fine", nullable = false)
    private boolean fine;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "issued_date",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate issuedDate;

    @Column(name = "valid_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate validDate;

}
