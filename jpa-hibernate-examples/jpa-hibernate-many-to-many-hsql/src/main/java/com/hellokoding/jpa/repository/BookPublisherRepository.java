package com.hellokoding.jpa.repository;

import com.hellokoding.jpa.model.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPublisherRepository extends JpaRepository<BookPublisher, Integer> {
}
