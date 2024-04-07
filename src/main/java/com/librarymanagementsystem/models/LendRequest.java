package com.librarymanagementsystem.models;

import java.math.BigInteger;
import java.util.List;

public class LendRequest {
    private BigInteger userId;
    private List<Book> bookList;

    public LendRequest() {
    }

    public LendRequest(BigInteger userId, List<Book> bookList) {
        this.userId = userId;
        this.bookList = bookList;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
