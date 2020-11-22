package com.hellokoding.jpa.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeletingDataTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private int givenCategoryId;

    @Before
    public void setUp(){
        // given
        givenCategoryId = testEntityManager.persistAndFlush(new Category("A", new Book("A1"), new Book("A2"))).getId();
    }

    @Test
    public void whenDeleteAssociatedEntitiesByCascadeType_thenSuccess() {
        // when
        Category category = categoryRepository.findById(givenCategoryId).get();
        categoryRepository.delete(category);

        // then
        assertThat(bookRepository.findByCategoryId(givenCategoryId)).hasSize(0);
    }

    @Test
    public void whenDeleteAssociatedEntitiesByOrphanRemoval_thenSuccess() {
        // when
        Category category = categoryRepository.findById(givenCategoryId).get();
        Book book = category.getBooks().iterator().next();
        int bookId = book.getId();
        category.getBooks().remove(book);
        categoryRepository.flush();

        // then
        assertThat(bookRepository.findById(bookId)).isEmpty();
    }

    @Test
    public void whenDeleteAssociatedEntitiesByJPQL_thenSuccess() {
        // when
        bookRepository.deleteByCategoryId(givenCategoryId);

        // then
        assertThat(bookRepository.findByCategoryId(givenCategoryId)).hasSize(0);
    }
}
