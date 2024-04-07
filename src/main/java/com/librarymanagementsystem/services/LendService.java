package com.librarymanagementsystem.services;

import com.librarymanagementsystem.models.Book;
import com.librarymanagementsystem.models.Lend;
import com.librarymanagementsystem.repository.LendRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class LendService {
    private final LendRepository lendRepository;

    public LendService(LendRepository lendRepository) {
        this.lendRepository = lendRepository;
    }

    public Lend lendBooks(BigInteger userId, List<Book> bookList) {
        Lend lend = new Lend.Builder()
                .userId(userId)
                .bookList(bookList)
                .build();
        return lendRepository.save(lend);
    }

    public void returnBook(BigInteger lendId) {
        Optional<Lend> lendOptional = lendRepository.findById(lendId);
        if (lendOptional.isEmpty()) {
            throw new IllegalArgumentException("Lend with ID " + lendId + " not found");
        }
        Lend lend = lendOptional.get();
        lendRepository.delete(lend);
    }
}
