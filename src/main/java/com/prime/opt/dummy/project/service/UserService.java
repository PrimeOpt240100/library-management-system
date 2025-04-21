package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.request.AddUserRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.AddUserResponse;

public interface UserService {

    BaseResponse<AddUserResponse> addNewUser(AddUserRequest addUserRequest);

}
