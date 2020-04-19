package com.hellokoding.jpa.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;

    public BookService(BookCategoryRepository bookCategoryRepository,
                       BookRepository bookRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
        this.bookRepository = bookRepository;
    }

    public void create() {
        List<BookCategory> bookCategories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BookCategory bookCategory = new BookCategory("Category " + (i+1));
            for (int j = 0; j < 2; j++) {
                Book book = new Book(String.format("Book %s.%s", (i+1), (j+1)));
                book.setBookCategory(bookCategory);
            }
            bookCategories.add(bookCategory);
        }

        bookCategoryRepository.saveAll(bookCategories);
    }

    public void read() {
        List<Book> books = bookRepository.findFirst10ByOrderByNameAsc();
        books.forEach(b -> System.out.println(
            String.format("%s, %s", b.getName(), b.getBookCategory().getName())
        ));
    }

    public void delete() {
        bookRepository.deleteInBulkByCategoryId(1);
    }
}
