package com.hellokoding.jpa;

import com.hellokoding.jpa.book.Address;
import com.hellokoding.jpa.book.Library;
import com.hellokoding.jpa.book.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {
    private final LibraryRepository libraryRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        // Create a couple of Library and Address
        libraryRepository.save(new Library("Library 1", new Address("Street 1", "Zip 1")));
        libraryRepository.save(new Library("Library 2", new Address("Street 2", "Zip 2")));
    }
}
