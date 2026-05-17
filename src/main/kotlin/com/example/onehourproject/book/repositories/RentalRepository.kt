package com.example.onehourproject.book.repositories

import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.entitis.Rental
import org.springframework.data.jpa.repository.JpaRepository

interface RentalRepository : JpaRepository<Rental, Long> {
    fun existsByBookAndReturnDateIsNull(book: Book): Boolean
}
