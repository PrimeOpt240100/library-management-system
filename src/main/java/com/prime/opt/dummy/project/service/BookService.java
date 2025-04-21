package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.request.AddBookRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.AddBookResponse;

public interface BookService {

    BaseResponse<AddBookResponse> addNewBook(AddBookRequest addBookRequest);

}
