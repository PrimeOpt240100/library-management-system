package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.model.CustomUserDetails;
import com.prime.opt.dummy.project.request.RegisterUserRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.RegisterUserResponse;

public interface UserService {

    BaseResponse<RegisterUserResponse> addNewUser(RegisterUserRequest registerUserRequest);

    BaseResponse<CustomUserDetails> getUserDetailsByUserId(String userId);

}
