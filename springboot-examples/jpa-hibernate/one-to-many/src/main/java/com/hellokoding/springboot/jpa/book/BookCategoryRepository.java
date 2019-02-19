package com.hellokoding.springboot.jpa.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer>{
}
