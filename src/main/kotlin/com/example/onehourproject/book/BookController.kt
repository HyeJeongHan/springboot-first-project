package com.example.onehourproject.book

import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.service.BookService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @GetMapping
    fun getBooks(): List<Book> = bookService.getBooks()

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
