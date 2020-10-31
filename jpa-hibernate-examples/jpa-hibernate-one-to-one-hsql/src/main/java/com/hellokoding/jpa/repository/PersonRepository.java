package com.hellokoding.jpa.repository;

import com.hellokoding.jpa.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer>{
}
