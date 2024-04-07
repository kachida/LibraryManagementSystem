package com.librarymanagementsystem.controllers;

import com.librarymanagementsystem.models.Lend;
import com.librarymanagementsystem.models.LendRequest;
import com.librarymanagementsystem.services.LendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;


@RestController
@RequestMapping("/api/lend")
public class LendController {
    private LendService lendService;

    public LendController(LendService lendService) {
        this.lendService = lendService;
    }

    @PostMapping
    public ResponseEntity<Lend> lendBooks(@RequestBody LendRequest lendRequest) {
        try {
            Lend lend = lendService.lendBooks(lendRequest.getUserId(), lendRequest.getBookList());
            return new ResponseEntity<>(lend,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
