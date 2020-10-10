package com.hellokoding.jpa.unidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer>{
}
