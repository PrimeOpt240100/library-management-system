package com.prime.opt.dummy.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prime.opt.dummy.project.Enum.book_enum.Language;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
public class NewBookEntryResponse {

    private String name;
    private String author;
    private String publisher;
    private String edition;
    private Language language;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate addedDate;

    private List<String> bookIdList;

}
