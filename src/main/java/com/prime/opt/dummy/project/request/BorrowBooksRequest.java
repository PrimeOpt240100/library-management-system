package com.prime.opt.dummy.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BorrowBooksRequest {

    @NotBlank(message = "cardId can't be blank or null")
    private String cardId;

    @NotEmpty(message = "Book Ids list can't be empty")
    @Size(max = 5, message = "You can borrow at most 5 books at a time")
    private List<String> bookIds;

}
