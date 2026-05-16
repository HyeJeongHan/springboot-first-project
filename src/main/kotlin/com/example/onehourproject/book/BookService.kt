package com.example.onehourproject.book

import com.example.onehourproject.book.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {
    private val books = mutableListOf<Book>()

    fun createBook(book: Book): Book {
        book.id = (books.size + 1).toLong()
        books.add(book)
        return book
    }

    fun getBooks(): List<Book> = books

    fun updateBook(id: Long, updatedBook: Book): Book? {
        val book = books.find { it.id == id } ?: return null
        book.title = updatedBook.title
        return book
    }

    fun deleteBook(id: Long): Boolean {
        return books.removeIf { it.id == id }
    }

    fun getBookById(id: Long): Book {
        return bookRepository.findById(id)
            .orElseThrow { NotFoundException("도서를 찾을 수 없습니다.") }
    }
}
