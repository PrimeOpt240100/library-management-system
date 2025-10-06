package com.prime.opt.dummy.project.response;

import com.prime.opt.dummy.project.model.BorrowBookDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BorrowBooksResponse {
    private String requestId;
    private String name;
    private String cardId;
    private List<BorrowBookDetails> bookDetailsList;
}
