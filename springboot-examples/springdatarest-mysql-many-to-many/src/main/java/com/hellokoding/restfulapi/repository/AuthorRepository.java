package com.hellokoding.restfulapi.repository;

import com.hellokoding.restfulapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
}
