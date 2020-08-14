package com.hellokoding.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    String name;
    @OneToMany(fetch = LAZY, mappedBy = "author", cascade = PERSIST)
    @Builder.Default Set<Quote> quotes = new HashSet<>();
}

interface AuthorProjection {
    Integer getId();
    String getName();
}

@AllArgsConstructor
class AuthorIdName {
    Integer id;
    String name;
}

interface AuthorRepository extends JpaRepository<Author, Integer> {}

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class Quote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    String name;
    @ManyToOne(fetch = LAZY) Author author;
}

interface QuoteProjection {
    Integer getId();
    String getName();
    AuthorProjection getAuthor();
}

@AllArgsConstructor
class QuoteWithAuthor {
    Integer id;
    String name;
    AuthorIdName author;

    static List<QuoteWithAuthor> of(List<Quote> quotes) {
        return quotes.stream().map(q -> {
            AuthorIdName ain = new AuthorIdName(q.author.id, q.author.name);
            return new QuoteWithAuthor(q.id, q.name, ain);
        }).collect(Collectors.toList());
    }
}

interface QuoteRepository extends JpaRepository<Quote, Integer> {
    @EntityGraph(attributePaths = "author")
    List<Quote> findFirst10ByOrderByName();

    @Query(value="FROM Quote q LEFT JOIN FETCH q.author")
    List<Quote> findWithAuthorAndJoinFetch(Pageable pageable);

    List<QuoteProjection> findFirst10ByOrderByNameDesc();
}

@Service @RequiredArgsConstructor
class QuoteService {
    private final AuthorRepository authorRepository;
    private final QuoteRepository quoteRepository;

    public void create() {
        Stream<Author> authors  = IntStream.range(1, 11).mapToObj(i -> {
            Author a = Author.builder().name("Author " + i).build();
            IntStream.range(1, 3).forEach(j -> {
                Quote q = Quote.builder().name(String.format("Quote %d.%d", i, j))
                    .author(a).build();
                a.quotes.add(q);
            });

            return a;
        });

        authorRepository.saveAll(authors.collect(Collectors.toList()));
    }

    public List<QuoteWithAuthor> findWithLazy() {
        return QuoteWithAuthor.of(quoteRepository.findAll(PageRequest.of(0, 10)).toList());
    }

    public List<QuoteWithAuthor> findWithEntityGraph() {
        return QuoteWithAuthor.of(quoteRepository.findFirst10ByOrderByName());
    }

    public List<QuoteWithAuthor> findWithJoinFetch() {
        return QuoteWithAuthor.of(quoteRepository.findWithAuthorAndJoinFetch(PageRequest.of(0, 10)));
    }

    public List<QuoteProjection> findWithAuthorProjection() {
        return quoteRepository.findFirst10ByOrderByNameDesc();
    }
}

@SpringBootApplication @RequiredArgsConstructor
public class QuoteApplication implements CommandLineRunner {
    private final QuoteService quoteService;

    public void run(String... args) {
        quoteService.create();
        quoteService.findWithAuthorProjection().forEach(q -> {
            System.out.println(q.getName() + " " + q.getAuthor().getName());
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(QuoteApplication.class, args);
    }
}
