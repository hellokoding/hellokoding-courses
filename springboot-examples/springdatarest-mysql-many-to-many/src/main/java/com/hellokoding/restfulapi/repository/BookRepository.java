package com.hellokoding.restfulapi.repository;

import com.hellokoding.restfulapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
}
