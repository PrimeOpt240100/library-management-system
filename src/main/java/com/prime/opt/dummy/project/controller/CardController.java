package com.prime.opt.dummy.project.controller;

import com.prime.opt.dummy.project.model.IssuedBookDetails;
import com.prime.opt.dummy.project.request.BaseResponse;
import com.prime.opt.dummy.project.request.BorrowBooksRequest;
import com.prime.opt.dummy.project.response.BorrowBooksResponse;
import com.prime.opt.dummy.project.service.CardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping(value = "/get/details")
    public ResponseEntity<BaseResponse<?>> getCardDetails(@RequestParam("cardId") String cardId){
        return null;
    }

    @PostMapping("/extend/validity")
    public ResponseEntity<BaseResponse<?>> extendCardValidity(@RequestParam("cardId") String cardId){
        return null;
    }

    @GetMapping("/get/fine-details")
    public ResponseEntity<BaseResponse<?>> getAllFineDetails(@RequestParam("cardId") String cardId) {
        return null;
    }

    @PostMapping("/place/request/borrow-book")
    public ResponseEntity<BaseResponse<BorrowBooksResponse>> placeBorrowBooksRequest(@Valid @RequestBody BorrowBooksRequest request){
        return ResponseEntity.ok(cardService.placeBorrowBookRequest(request));
    }

    @GetMapping("/find/issued-book")
    public ResponseEntity<BaseResponse<IssuedBookDetails>> getIssuedBooksDetails(@RequestParam("CardId") String cardId){
        return null;
    }

}
