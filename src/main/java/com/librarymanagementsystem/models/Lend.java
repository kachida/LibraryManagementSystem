package com.librarymanagementsystem.models;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Component
public class Lend {
    private  BigInteger lendId;
    private  BigInteger userId;
    private  List<Book> bookList;

    public Lend() {}

    private Lend(Builder builder) {
        this.userId = builder.userId;
        this.bookList = builder.bookList;
        this.lendId = builder.lendId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public List<Book> getBookList() {
        return Collections.unmodifiableList(bookList);
    }

    public BigInteger getLendId() {
        return lendId;
    }

    @Override
    public String toString() {
        return "Lend{" +
                "userId=" + userId +
                ", bookList=" + bookList +
                ", lendId= " + lendId +
                '}';
    }

    public static class Builder {
        private BigInteger lendId;
        private BigInteger userId;
        private List<Book> bookList;

        public Builder userId(BigInteger userId) {
            this.userId = userId;
            return this;
        }

        public Builder bookList(List<Book> bookList) {
            this.bookList = bookList;
            return this;
        }

        public Builder lendId(BigInteger lendId) {
            this.lendId = lendId;
            return this;
        }

        public Lend build() {
            return new Lend(this);
        }
    }
}
