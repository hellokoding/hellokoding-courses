package com.hellokoding.jpa.customer;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.id = ?1")
    void deleteByIdWithJPQL(int id);

    List<Customer> findTop10ByDeletedFalse();

    List<Customer> findTop10By();

    @EntityGraph(attributePaths = "cards", type = EntityGraph.EntityGraphType.LOAD)
    List<Customer> findAllByIdIsIn(List<Integer> ids);
}
