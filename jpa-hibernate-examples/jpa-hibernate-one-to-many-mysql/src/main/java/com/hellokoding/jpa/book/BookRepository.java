package com.hellokoding.jpa.book;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>{
    @EntityGraph(attributePaths = "bookCategory")
    List<Book> findFirst10ByOrderByNameAsc();

    @Modifying
    @Query("DELETE FROM Book b WHERE b.bookCategory.id = ?1")
    void deleteInBulkByCategoryId(int categoryId);

    void deleteByCategoryId(int categoryId);
}
