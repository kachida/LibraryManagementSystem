package com.librarymanagementsystem.services;


import com.librarymanagementsystem.models.Book;
import com.librarymanagementsystem.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void addBook() {
        Book bookToAdd = new Book();
        when(bookRepository.save(bookToAdd)).thenReturn(bookToAdd);

        Book addedBook = bookService.addBook(bookToAdd);
        Assertions.assertNotNull(addedBook);
        Assertions.assertEquals(addedBook, bookToAdd);
        Mockito.verify(bookRepository, times(1)).save(bookToAdd);
    }

    @Test
    void searchBook_WhenBookExists() {
        BigInteger bookId = BigInteger.valueOf(123);
        Book bookInRepo = new Book();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookInRepo));

        Book foundBook = bookService.searchBook(bookId);
        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals(foundBook, bookInRepo);
        Mockito.verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void searchBook_WhenBookNotExists() {
        BigInteger bookId = BigInteger.valueOf(123);
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Book foundBook = bookService.searchBook(bookId);
        Assertions.assertNull(foundBook);
        Mockito.verify(bookRepository, times(1)).findById(bookId);

    }

    @Test
    void searchAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book());
        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> listOfBooks = bookService.searchAllBooks();
        Assertions.assertNotNull(listOfBooks);
        Assertions.assertEquals(listOfBooks, bookList);
        Mockito.verify(bookRepository, times(1)).findAll();
    }

    @Test
    void updateBook_WhenBookExists() {
        Book existingBook = new Book();
        when(bookRepository.findById(existingBook.getBookId())).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book updatedBook = bookService.updateBook(existingBook);
        Assertions.assertNotNull(updatedBook);
        Assertions.assertEquals(existingBook, updatedBook);
        Mockito.verify(bookRepository, times(1)).findById(existingBook.getBookId());
        Mockito.verify(bookRepository, times(1)).save(existingBook);

    }

    @Test
    void updateBook_WhenBookNotExists() {
        Book nonExistingBook = new Book();
        when(bookRepository.findById(nonExistingBook.getBookId())).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, ()->bookService.updateBook(nonExistingBook));
        Mockito.verify(bookRepository, times(1)).findById(nonExistingBook.getBookId());
        Mockito.verify(bookRepository, never()).save(any());
    }

    @Test
    void deleteBook_WhenBookExists() {
        BigInteger bookId = BigInteger.valueOf(123);
        Book bookToDelete = new Book();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookToDelete));

        boolean deleted = bookService.deleteBook(bookId);
        Assertions.assertTrue(deleted);
        Mockito.verify(bookRepository, times(1)).findById(bookId);
        Mockito.verify(bookRepository, times(1)).delete(bookToDelete);

    }

    @Test
    void deleteBook_WhenBookNotExists() {
        BigInteger bookId = BigInteger.valueOf(123);
        Book nonExistingBook = new Book();
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        boolean deleted = bookService.deleteBook(bookId);
        Assertions.assertFalse(deleted);
        Mockito.verify(bookRepository, times(1)).findById(bookId);
        Mockito.verify(bookRepository, never()).delete(nonExistingBook);
    }
}
