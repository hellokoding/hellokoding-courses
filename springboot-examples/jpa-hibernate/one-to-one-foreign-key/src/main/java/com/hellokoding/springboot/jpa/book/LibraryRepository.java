package com.hellokoding.springboot.jpa.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer>{
}
