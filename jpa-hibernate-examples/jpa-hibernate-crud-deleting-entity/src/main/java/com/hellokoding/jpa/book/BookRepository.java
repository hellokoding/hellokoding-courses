package com.hellokoding.jpa.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Modifying
    @Query("DELETE Book b WHERE b.category.id = ?1")
    void deleteByCategoryId(int categoryId);

    List<Book> findByCategoryId(int categoryId);
}
