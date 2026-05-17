package com.example.onehourproject.book.repositories

import com.example.onehourproject.book.entitis.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface BookRepository : JpaRepository<Book, Long> {
    fun findByTitle(title: String): List<Book>
    fun findByAuthor(author: String): List<Book>
    fun findByPublishedDate(publishedDate: LocalDate): List<Book>
    fun findByAuthorAndPublishedDate(author: String, publishedDate: LocalDate): List<Book>
}
