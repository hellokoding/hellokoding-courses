package com.hellokoding.springboot.jpa.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
}
