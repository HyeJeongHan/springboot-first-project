package com.example.onehourproject.book.repositories

import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.entitis.Rental
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RentalRepository : JpaRepository<Rental, Long> {
    fun existsByBookAndReturnDateIsNull(book: Book): Boolean
    fun findByIdAndReturnedDateIsNull(bookId: Long): Optional<Rental>
}
