package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.model.IssuedBookDetails;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.request.BorrowBooksRequest;
import com.prime.opt.dummy.project.response.BorrowBooksResponse;
import com.prime.opt.dummy.project.response.NewCardResponse;

import java.util.List;

public interface CardService {

    BaseResponse<NewCardResponse> applyForNewCard();

    BaseResponse<BorrowBooksResponse> placeBorrowBookRequest(BorrowBooksRequest borrowBooksRequest);

    BaseResponse<List<IssuedBookDetails>> getIssuedBookDetails(String cardId);

}
