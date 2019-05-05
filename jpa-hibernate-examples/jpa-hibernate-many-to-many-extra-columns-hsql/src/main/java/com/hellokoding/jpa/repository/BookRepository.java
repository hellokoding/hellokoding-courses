package com.hellokoding.jpa.repository;

import com.hellokoding.jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
}
