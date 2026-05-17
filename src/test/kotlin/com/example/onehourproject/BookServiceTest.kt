package com.example.onehourproject

import com.example.onehourproject.book.BookRequest
import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.service.BookService
import com.example.onehourproject.book.exception.NotFoundException
import com.example.onehourproject.book.repositories.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import java.time.LocalDate

class BookServiceTest {
    private lateinit var bookService: BookService

    @BeforeEach
    fun setUp() {
        val bookRepository = mock(BookRepository::class.java)
        bookService = BookService(bookRepository)
    }

    @Test
    fun 책을_생성한다() {
        val bookRequest = BookRequest(title = "테스트 책", author = "hong", LocalDate.of(2000, 3, 4))
        val saved = bookService.createBook(bookRequest)

        assertThat(saved.id).isNotNull()
        assertThat(saved.title).isEqualTo("테스트 책")
        assertThat(bookService.getBooks()).hasSize(1)
    }

    @Test
    fun 책을_수정한다() {
        val bookRequest = BookRequest(title = "테스트 책", author = "hong", LocalDate.of(2000, 3, 4))
        bookService.createBook(bookRequest)

        val updated = bookService.updateBook(1L, Book(title = "수정된 책"))

        assertThat(updated?.title).isEqualTo("수정된 책")
    }

    @Test
    fun 책을_삭제한다() {
        val bookRequest = BookRequest(title = "삭제할 책", author = "hong", LocalDate.of(2000, 3, 4))
        bookService.createBook(bookRequest)

        val deleted = bookService.deleteBook(1L)

        assertThat(deleted).isTrue()
        assertThat(bookService.getBooks()).isEmpty()
    }

    @Test
    fun 책을_조회한다() {
        assertThatThrownBy { bookService.getBookById(11L) }
            .isInstanceOf(NotFoundException::class.java)
            .hasMessage("도서를 찾을 수 없습니다.")
    }
}
