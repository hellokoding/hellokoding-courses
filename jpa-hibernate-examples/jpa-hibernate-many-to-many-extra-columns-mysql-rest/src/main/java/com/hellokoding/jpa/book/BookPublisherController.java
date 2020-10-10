package com.hellokoding.jpa.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookPublishers")
@RequiredArgsConstructor
public class BookPublisherController {
    private final BookPublisherRepository bookPublisherRepository;

    @PostMapping
    public ResponseEntity<BookPublisher> create(@RequestBody BookPublisher bookPublisher) {
        BookPublisher savedBookPublisher = bookPublisherRepository.save(bookPublisher);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookPublisher);
    }
}
