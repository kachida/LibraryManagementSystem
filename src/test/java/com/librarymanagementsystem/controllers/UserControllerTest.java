package com.librarymanagementsystem.controllers;

import com.librarymanagementsystem.models.User;
import com.librarymanagementsystem.services.UserService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void shouldReturnExistingUser() throws Exception {
        BigInteger userId = BigInteger.valueOf(123);
        User user = new User.Builder()
                .userId(BigInteger.valueOf(123))
                .name("Kanna")
                .email("kanna@test.com")
                .phone("test")
                .build();
        when(userService.getUser(userId)).thenReturn(user);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users/{userId}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(userId.intValue()))
                .andExpect(jsonPath("$.name").value("Kanna"))
                .andExpect(jsonPath("$.email").value("kanna@test.com"))
                .andExpect(jsonPath("$.phone").value("test"));
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        BigInteger userId = BigInteger.valueOf(123);
        User user = new User.Builder()
                .userId(BigInteger.valueOf(123))
                .name("Kanna")
                .email("kanna@test.com")
                .phone("123456789")
                .build();
        when(userService.createUser(user)).thenReturn(user);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
