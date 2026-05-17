package com.example.onehourproject.book.service

import com.example.onehourproject.book.entitis.Rental
import com.example.onehourproject.book.exception.AlreadyExistsException
import com.example.onehourproject.book.exception.NotFoundException
import com.example.onehourproject.book.repositories.BookRepository
import com.example.onehourproject.book.repositories.MembersRepository
import com.example.onehourproject.book.repositories.RentalRepository
import org.springframework.stereotype.Service

@Service
class RentalService(
    private val bookRepository: BookRepository,
    private val membersRepository: MembersRepository,
    private val rentalRepository: RentalRepository,
) {
    fun rentBook(bookId: Long, userId: Long) {
        val book = bookRepository.findById(bookId)
            .orElseThrow { NotFoundException("해당하는 책이 없습니다.") }
        val member = membersRepository.findById(userId)
            .orElseThrow { NotFoundException("사용자가 없습니다.") }

        if (rentalRepository.existsByBookAndReturnDateIsNull(book))
            throw AlreadyExistsException("이미 대여 중입니다.")

        rentalRepository.save(Rental(members = member, book = book))
    }
}
