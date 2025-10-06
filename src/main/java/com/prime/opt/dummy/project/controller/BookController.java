package com.prime.opt.dummy.project.controller;

import com.prime.opt.dummy.project.request.BookStockBatchRequest;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.response.NewBookEntryResponse;
import com.prime.opt.dummy.project.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/add/new")
    public ResponseEntity<BaseResponse<List<NewBookEntryResponse>>> registerBookStock(@Valid @RequestBody BookStockBatchRequest bookStockBatchRequest){
        return new ResponseEntity<>(bookService.registerBookStock(bookStockBatchRequest.getNewBookEntryRequestList()), HttpStatus.OK);
    }
}
