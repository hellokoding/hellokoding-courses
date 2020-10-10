package com.hellokoding.jpa.bidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer>{
}
