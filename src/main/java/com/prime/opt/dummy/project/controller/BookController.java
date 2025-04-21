package com.prime.opt.dummy.project.controller;

import com.prime.opt.dummy.project.request.AddBookRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.AddBookResponse;
import com.prime.opt.dummy.project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/add/new")
    public ResponseEntity<BaseResponse<AddBookResponse>> addNewBook(@RequestBody AddBookRequest addBookRequest){
        return new ResponseEntity<>(bookService.addNewBook(addBookRequest), HttpStatus.OK);
    }
}
