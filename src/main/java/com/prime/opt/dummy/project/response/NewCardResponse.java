package com.prime.opt.dummy.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.opt.dummy.project.Enum.card_enum.CardType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCardResponse {

    private String cardId;
    private String name;
    private CardType cardType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate issuedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate validDate;

}
