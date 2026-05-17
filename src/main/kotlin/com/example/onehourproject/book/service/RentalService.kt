package com.example.onehourproject.book.service

import com.example.onehourproject.book.dto.RentalRequest
import com.example.onehourproject.book.entitis.Rental
import com.example.onehourproject.book.exception.AlreadyExistsException
import com.example.onehourproject.book.exception.NotFoundException
import com.example.onehourproject.book.repositories.BookRepository
import com.example.onehourproject.book.repositories.MembersRepository
import com.example.onehourproject.book.repositories.RentalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class RentalService(
    private val bookRepository: BookRepository,
    private val membersRepository: MembersRepository,
    private val rentalRepository: RentalRepository,
) {
    @Transactional
    fun rentBook(request: RentalRequest) {
        val book = bookRepository.findById(request.bookId)
            .orElseThrow { NotFoundException("해당하는 책이 없습니다.") }
        val member = membersRepository.findById(request.memberId)
            .orElseThrow { NotFoundException("사용자가 없습니다.") }

        if (rentalRepository.existsByBookAndReturnDateIsNull(book))
            throw AlreadyExistsException("이미 대여 중입니다.")

        rentalRepository.save(Rental(members = member, book = book))
    }

    @Transactional
    fun returnBook(rentId: Long) {
        val rental = rentalRepository.findByIdAndReturnedDateIsNull(rentId)
            .orElseThrow { NotFoundException("대여중인 책이 없습니다.") }
        rental.returnDate = LocalDate.now()
    }
}
