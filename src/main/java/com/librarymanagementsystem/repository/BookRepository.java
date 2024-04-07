package com.librarymanagementsystem.repository;

import com.librarymanagementsystem.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BookRepository extends CrudRepository<Book, BigInteger> {
}
