package com.hellokoding.jpa.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Integer> {
    @Modifying
    @Query("DELETE Card c WHERE c.customer.id = ?1")
    void deleteByCustomerId(int customerId);
}
