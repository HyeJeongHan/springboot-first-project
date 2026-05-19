package com.example.onehourproject.book.repositories

import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.entitis.Rental
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RentalRepository : JpaRepository<Rental, Long> {
    fun existsByBookAndReturnDateIsNull(book: Book): Boolean
    fun findByIdAndReturnDateIsNull(bookId: Long): Optional<Rental>
    @EntityGraph(attributePaths = ["book", "members"])
    override fun findAll(pageable: Pageable): Page<Rental>
}
