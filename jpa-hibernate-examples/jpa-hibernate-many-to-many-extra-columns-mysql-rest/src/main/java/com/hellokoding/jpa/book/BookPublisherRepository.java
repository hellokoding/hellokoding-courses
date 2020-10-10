package com.hellokoding.jpa.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookPublisherRepository extends JpaRepository<BookPublisher, Integer>{
    List<BookPublisher> findByPublisherId(Integer publisherId);
}
