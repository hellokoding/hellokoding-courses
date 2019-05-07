package com.hellokoding.restfulapi.repository;

import com.hellokoding.restfulapi.model.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPublisherRepository extends JpaRepository<BookPublisher, Integer>{
}
