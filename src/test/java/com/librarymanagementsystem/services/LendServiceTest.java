package com.librarymanagementsystem.services;

import com.librarymanagementsystem.models.Book;
import com.librarymanagementsystem.models.Lend;
import com.librarymanagementsystem.repository.LendRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LendServiceTest {

    @InjectMocks
    private LendService lendService;

    @Mock
    private LendRepository lendRepository;

    @Test
    public void lendBooks_Success() {
        BigInteger userId = BigInteger.valueOf(123);
        List<Book> bookList = Arrays.asList(new Book(), new Book());

        Lend lend = new Lend.Builder()
                .lendId(BigInteger.valueOf(45678))
                .userId(userId)
                .bookList(bookList)
                .build();
        when(lendRepository.save(any(Lend.class))).thenReturn(lend);

        Lend result = lendService.lendBooks(userId, bookList);

        Assertions.assertEquals(lend, result);
        Mockito.verify(lendRepository, times(1)).save(any(Lend.class));
    }

    @Test
    public void returnBook_Success() {

        BigInteger lendId = BigInteger.valueOf(123);
        Lend lend = new Lend.Builder().lendId(lendId).build();
        when(lendRepository.findById(lendId)).thenReturn(Optional.of(lend));

        lendService.returnBook(lendId);

        Mockito.verify(lendRepository, times(1)).findById(lendId);
        Mockito.verify(lendRepository, times(1)).delete(lend);
    }

    @Test
    public void returnBook_NotFound() {
        BigInteger lendId = BigInteger.valueOf(123);
        when(lendRepository.findById(lendId)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> lendService.returnBook(lendId));

        Mockito.verify(lendRepository, times(1)).findById(lendId);
        Mockito.verify(lendRepository, never()).delete(any(Lend.class));
    }

}
