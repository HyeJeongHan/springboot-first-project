package com.example.onehourproject.book.service

import com.example.onehourproject.book.dto.BookRequest
import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.exception.NotFoundException
import com.example.onehourproject.book.repositories.BookRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Slf4j
@Service
class BookService(private val bookRepository: BookRepository) {

    private val log = LoggerFactory.getLogger(BookService::class.java)

    fun createBook(bookRequest: BookRequest): Book {
        return bookRepository.save(Book(
            title = bookRequest.title,
            author = bookRequest.author,
            publishedDate = bookRequest.publishedDate
        ))
    }

    @Cacheable("books")
    fun getBooks(title: String?, author: String?, pageable: Pageable): Page<Book> {
        log.info("데이터베이스 조회, 캐시미스")
        return if (title != null && author != null) {
            bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author, pageable)
        } else if (title != null) {
            bookRepository.findByTitleContainingIgnoreCase(title, pageable)
        } else if (author != null) {
            bookRepository.findByAuthorContainingIgnoreCase(author, pageable)
        } else {
            bookRepository.findAll(pageable)
        }
    }

    @CacheEvict(value = ["books"], allEntries = true)
    fun updateBook(id: Long, updatedBook: Book): Book? {
        val book = bookRepository.save(updatedBook)
        log.info("ID ${updatedBook.id} 의 캐시를 초기화 했습니다.")
        return book
    }

    @CacheEvict(value = ["books"], allEntries = true)
    fun deleteBook(id: Long): Boolean {
        if (!bookRepository.existsById(id)) return false
        bookRepository.deleteById(id)
        log.info("ID ${id} 의 캐시가 삭제되었습니다.")
        return true
    }

    @Cacheable("books", key = "#id")
    fun getBookById(id: Long): Book {
        return bookRepository.findById(id)
            .orElseThrow { NotFoundException("도서를 찾을 수 없습니다.") }
    }
}
