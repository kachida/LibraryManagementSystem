package com.librarymanagementsystem.models;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class Book {
    private BigInteger bookId;
    private String bookName;
    private String authorName;

    public Book() {
    }

    public Book(Builder builder) {
        this.bookId = builder.bookId;
        this.bookName = builder.bookName;
        this.authorName = builder.authorName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }

    public BigInteger getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public static class Builder {
        private BigInteger bookId;
        private String bookName;
        private String authorName;

        public Builder bookId(BigInteger bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder bookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public Builder authorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

}
