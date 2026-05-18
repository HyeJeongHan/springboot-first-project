package com.example.onehourproject.book.service

import com.example.onehourproject.book.dto.BookRequest
import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.exception.NotFoundException
import com.example.onehourproject.book.repositories.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun createBook(bookRequest: BookRequest): Book {
        return bookRepository.save(Book(
            title = bookRequest.title,
            author = bookRequest.author,
            publishedDate = bookRequest.publishedDate
        ))
    }

    fun getBooks(title: String?, author: String?, pageable: Pageable): Page<Book> {
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

    fun updateBook(id: Long, updatedBook: Book): Book? {
        val book = bookRepository.findById(id).orElse(null) ?: return null
        book.title = updatedBook.title
        return bookRepository.save(book)
    }

    fun deleteBook(id: Long): Boolean {
        if (!bookRepository.existsById(id)) return false
        bookRepository.deleteById(id)
        return true
    }

    fun getBookById(id: Long): Book {
        return bookRepository.findById(id)
            .orElseThrow { NotFoundException("도서를 찾을 수 없습니다.") }
    }
}
