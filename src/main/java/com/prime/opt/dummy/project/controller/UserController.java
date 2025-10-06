package com.prime.opt.dummy.project.controller;

import com.prime.opt.dummy.project.model.CustomUserDetails;
import com.prime.opt.dummy.project.request.RegisterUserRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.RegisterUserResponse;
import com.prime.opt.dummy.project.response.NewCardResponse;
import com.prime.opt.dummy.project.service.CardService;
import com.prime.opt.dummy.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {


    private final UserService userService;

    private final CardService cardService;

    public UserController(UserService userService, CardService cardService){
        this.userService = userService;
        this.cardService = cardService;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<RegisterUserResponse>> addNewUser(@Valid @RequestBody RegisterUserRequest request){
        return new ResponseEntity<>(userService.addNewUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<BaseResponse<CustomUserDetails>> findUserById(@PathVariable(value = "userId") String userId){
        return new ResponseEntity<>(userService.getUserDetailsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/apply/card")
    public ResponseEntity<BaseResponse<NewCardResponse>> applyForNewCard(){
        return new ResponseEntity<>(cardService.applyForNewCard(), HttpStatus.OK);
    }
}
