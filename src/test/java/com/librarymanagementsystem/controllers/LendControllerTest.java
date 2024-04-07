package com.librarymanagementsystem.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagementsystem.controllers.LendController;
import com.librarymanagementsystem.models.Book;
import com.librarymanagementsystem.models.Lend;
import com.librarymanagementsystem.models.LendRequest;
import com.librarymanagementsystem.services.LendService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LendController.class)
public class LendControllerTest {

    @MockBean
    private LendService lendService;

    @Autowired
    private MockMvc mockMvc;


    // Todo: Error in below test file, debug and fix it
    @Test
    public void lendBooks_Success() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        BigInteger userId = BigInteger.valueOf(123);
        Book book1 = new Book.Builder()
                .bookId(BigInteger.valueOf(1))
                .bookName("Book 1")
                .authorName("test")
                .build();
        List<Book> bookList = Collections.singletonList(book1);

        Lend lend = new Lend.Builder()
                .userId(userId)
                .bookList(bookList)
                .build();

        when(lendService.lendBooks(userId, bookList)).thenReturn(lend);

        LendRequest lendRequest = new LendRequest(userId, bookList);

        mockMvc.perform(post("/api/lend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lendRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(lend)));

        verify(lendService).lendBooks(userId, bookList);
    }
}
