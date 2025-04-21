package com.prime.opt.dummy.project.controller;

import com.prime.opt.dummy.project.request.AddUserRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.AddUserResponse;
import com.prime.opt.dummy.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add/new")
    public ResponseEntity<BaseResponse<AddUserResponse>> addNewUser(@RequestBody AddUserRequest request){
        return new ResponseEntity<>(userService.addNewUser(request), HttpStatus.OK);
    }
}
