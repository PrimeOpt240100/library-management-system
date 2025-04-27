package com.prime.opt.dummy.project.controller;

import com.prime.opt.dummy.project.request.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

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

}
