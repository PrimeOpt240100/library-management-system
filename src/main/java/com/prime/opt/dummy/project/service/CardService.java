package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.request.NewCardRequest;
import com.prime.opt.dummy.project.response.NewCardResponse;

public interface CardService {

    BaseResponse<NewCardResponse> applyForNewCard(NewCardRequest newCardRequest);

}
