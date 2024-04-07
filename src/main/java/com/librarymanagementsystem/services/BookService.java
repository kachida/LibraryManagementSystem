package com.librarymanagementsystem.services;

import com.librarymanagementsystem.models.Book;
import com.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        if(book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        return bookRepository.save(book);
    }

    public Book searchBook(BigInteger bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(!bookOptional.isPresent()) {
            return null;
        }
        return bookOptional.get();
    }

    public List<Book> searchAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book updateBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getBookId());
        if (existingBook.isEmpty()) {
            throw new IllegalArgumentException("Book with id : " + book.getBookId());
        }
        return bookRepository.save(book);
    }

    public boolean deleteBook(BigInteger bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()) {
            return false;
        }
        bookRepository.delete(optionalBook.get());
        return true;
    }

}
