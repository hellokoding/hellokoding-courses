package com.hellokoding.jpa.library;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/libraries")
public class LibraryController {
    private final LibraryRepository libraryRepository;

    public LibraryController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Library> create(@Valid @RequestBody Library library) {
        return ResponseEntity.ok(libraryRepository.save(library));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> update(@PathVariable Integer id, @Valid @RequestBody Library library) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        library.setId(optionalLibrary.get().getId());

        return ResponseEntity.ok(libraryRepository.save(library));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Library> delete(@PathVariable Integer id) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        libraryRepository.delete(optionalLibrary.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> get(@PathVariable Integer id) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalLibrary.get());
    }

    @GetMapping("/")
    public ResponseEntity<Page<Library>> getAll(Pageable pageable) {
        return ResponseEntity.ok(libraryRepository.findAll(pageable));
    }
}
