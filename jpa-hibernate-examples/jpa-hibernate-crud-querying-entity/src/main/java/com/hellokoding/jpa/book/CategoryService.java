package com.hellokoding.jpa.book;

import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory() {
        Book[] books = new Book[10];
        for (int i = 0; i < 10; i++) {
            books[i] = new Book("A" + i);
        }

        categoryRepository.save(new Category("A", books));
    }

    @Transactional
    public void testSortingAssociatedCollectionWithOrderBy() {
        Category category = categoryRepository.findById(1).get();
        Assert.assertEquals(10, category.getBooks().size());
        Assert.assertEquals("A9", category.getBooks().iterator().next().getName());
    }
}
