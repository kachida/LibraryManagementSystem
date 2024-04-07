package com.librarymanagementsystem.controllers;

import com.librarymanagementsystem.models.Book;
import com.librarymanagementsystem.models.User;
import com.librarymanagementsystem.services.BookService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable("bookId") BigInteger bookId) {
        Book book = bookService.searchBook(bookId);
        if ( book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.searchAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok().body(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") BigInteger bookId) {
        boolean deleted = bookService.deleteBook(bookId);
        if (deleted) {
            return ResponseEntity.ok().body("Book deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
