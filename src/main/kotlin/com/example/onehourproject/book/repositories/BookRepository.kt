package com.example.onehourproject.book.repositories

import com.example.onehourproject.book.entitis.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface BookRepository : JpaRepository<Book, Long> {
    fun findByTitleContainingIgnoreCase(title: String, pageable: Pageable): Page<Book>
    fun findByAuthorContainingIgnoreCase(author: String, pageable: Pageable): Page<Book>
    fun findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title: String, author: String, pageable: Pageable): Page<Book>
    fun findByPublishedDate(publishedDate: LocalDate): List<Book>
    fun findByAuthorAndPublishedDate(author: String, publishedDate: LocalDate): List<Book>
}
