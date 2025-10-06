package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.request.NewBookEntryRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.NewBookEntryResponse;

import java.util.List;

public interface BookService {

    BaseResponse<List<NewBookEntryResponse>> registerBookStock(List<NewBookEntryRequest> newBookEntryRequest);

}
