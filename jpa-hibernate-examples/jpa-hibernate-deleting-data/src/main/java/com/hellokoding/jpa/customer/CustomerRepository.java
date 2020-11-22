package com.hellokoding.jpa.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Modifying
    @Query("DELETE Customer c WHERE c.id = ?1")
    void deleteByIdWithJPQL(int id);
}
