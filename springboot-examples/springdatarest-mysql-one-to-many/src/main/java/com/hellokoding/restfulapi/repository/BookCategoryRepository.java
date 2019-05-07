package com.hellokoding.restfulapi.repository;

import com.hellokoding.restfulapi.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer>{
}
