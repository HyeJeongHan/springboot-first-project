package com.example.onehourproject;

import com.example.onehourproject.book.Book;
import com.example.onehourproject.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
    void 책을_생성한다() {
        String bookName = "테스트 책";
        Book book = createBook(bookName);

        Book saved = bookService.createBook(book);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(bookName);
        assertThat(bookService.getBooks()).hasSize(1);
    }

    @Test
    void 책을_수정한다() {
        String bookName = "테스트 책";
        Book book = createBook(bookName);

        bookService.createBook(book);

        String updateBookName = "수정된 책";
        Book update = createBook(updateBookName);

        Book updateBook = bookService.updateBook(1L, update);

        assertThat(updateBook.getTitle()).isEqualTo(updateBookName);
    }


    @Test
    void 책을_삭제한다() {
        Book book = createBook("삭제할 책");
        bookService.createBook(book);

        boolean deleted = bookService.deleteBook(1L);

        assertThat(deleted).isTrue();
        assertThat(bookService.getBooks()).isEmpty();
    }

    Book createBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        return book;
    }
}
