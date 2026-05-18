package com.example.onehourproject.book.controller

import com.example.onehourproject.book.dto.BookRequest
import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.service.BookService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<Book> {
        val book = bookService.getBookById(id)
        return ResponseEntity.ok(book)
    }

    @GetMapping
    fun getBooks(@RequestParam(required = false) title: String?, @RequestParam(required = false) author: String?, pageable: Pageable): ResponseEntity<Page<Book>> {
        val books: Page<Book> = bookService.getBooks(title, author, pageable)
        return ResponseEntity.ok(books)
    }

    @PostMapping
    fun createBook(@RequestBody @Valid bookRequest: BookRequest): ResponseEntity<Book> =
        ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookRequest))

    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody updateBook: Book): ResponseEntity<Book> {
        val updatedBook = bookService.updateBook(id, updateBook) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        return ResponseEntity.ok(updatedBook)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long): ResponseEntity<Void> {
        if (!bookService.deleteBook(id)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        return ResponseEntity.noContent().build()
    }
}