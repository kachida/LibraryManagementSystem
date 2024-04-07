package com.librarymanagementsystem.controllers;

import com.librarymanagementsystem.models.Book;
import com.librarymanagementsystem.services.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;


    @Test
    public void shouldCreateNewBook() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        BigInteger bookId = BigInteger.valueOf(123);
        Book book = new Book.Builder()
                .bookId(BigInteger.valueOf(123))
                .bookName("Harry potter and chamber of secrets")
                .authorName("J.K Rowling")
                .build();
        when(bookService.addBook(book)).thenReturn(book);
        String jsonBook = objectMapper.writeValueAsString(book);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook))
                .andExpect(status().isCreated());
    }

    @Test
    public void getBook_ExistingBookId_ReturnsBook() throws Exception {
        BigInteger bookId = BigInteger.valueOf(123);
        Book book = new Book.Builder()
                .bookId(bookId)
                .bookName("Test Book")
                .build();

        when(bookService.searchBook(bookId)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{bookId}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(bookId.intValue()))
                .andExpect(jsonPath("$.bookName").value("Test Book"));
    }

    @Test
    public void getBook_NonExistingBookId_ReturnsNotFound() throws Exception {
        BigInteger nonExistingBookId = BigInteger.valueOf(456);

        when(bookService.searchBook(nonExistingBookId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{bookId}", nonExistingBookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllBooks_ReturnsListOfBooks() throws Exception {
        Book book1 = new Book.Builder()
                .bookId(BigInteger.valueOf(1))
                .bookName("Book 1")
                .build();
        Book book2 = new Book.Builder()
                .bookId(BigInteger.valueOf(2))
                .bookName("Book 2")
                .build();
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.searchAllBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteBook_ExistingBookId_ReturnsSuccessMessage() throws Exception {
        BigInteger bookId = BigInteger.valueOf(123);

        when(bookService.deleteBook(bookId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{bookId}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Book deleted successfully"));
    }

    @Test
    public void deleteBook_NonExistingBookId_ReturnsNotFound() throws Exception {
        BigInteger nonExistingBookId = BigInteger.valueOf(456);

        when(bookService.deleteBook(nonExistingBookId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{bookId}", nonExistingBookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateBook_Success() throws Exception {
        BigInteger bookId = BigInteger.valueOf(123);
        ObjectMapper objectMapper = new ObjectMapper();
        Book updatedBook = new Book.Builder().bookId(bookId).bookName("Updated Book Title").build();

        when(bookService.updateBook(updatedBook)).thenReturn(updatedBook);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk());
    }

}
