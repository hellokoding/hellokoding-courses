package com.hellokoding.restfulapi.repository;

import com.hellokoding.restfulapi.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
}
