package com.prime.opt.dummy.project.controller;

import com.prime.opt.dummy.project.model.UserDetails;
import com.prime.opt.dummy.project.request.AddUserRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.request.NewCardRequest;
import com.prime.opt.dummy.project.response.AddUserResponse;
import com.prime.opt.dummy.project.response.NewCardResponse;
import com.prime.opt.dummy.project.service.CardService;
import com.prime.opt.dummy.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    private final CardService cardService;

    public UserController(UserService userService, CardService cardService){
        this.userService = userService;
        this.cardService = cardService;
    }

    @PostMapping("/add/new")
    public ResponseEntity<BaseResponse<AddUserResponse>> addNewUser(@RequestBody AddUserRequest request){
        return new ResponseEntity<>(userService.addNewUser(request), HttpStatus.OK);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<BaseResponse<UserDetails>> findUserById(@PathVariable(value = "userId") String userId){
        return new ResponseEntity<>(userService.getUserDetailsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/apply/card")
    public ResponseEntity<BaseResponse<NewCardResponse>> applyForNewCard(@RequestBody NewCardRequest request){
        return new ResponseEntity<>(cardService.applyForNewCard(request), HttpStatus.OK);
    }
}
