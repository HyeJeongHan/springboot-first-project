package com.example.onehourproject

import com.example.onehourproject.book.dto.RentalRequest
import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.entitis.Members
import com.example.onehourproject.book.entitis.Rental
import com.example.onehourproject.book.exception.AlreadyExistsException
import com.example.onehourproject.book.repositories.BookRepository
import com.example.onehourproject.book.repositories.MembersRepository
import com.example.onehourproject.book.repositories.RentalRepository
import com.example.onehourproject.book.service.RentalService
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.util.*
import kotlin.test.Test
import kotlin.test.assertNotNull


@ExtendWith(MockitoExtension::class)
class RentalServiceMockingTest {
    @InjectMocks
    lateinit var rentalService: RentalService
    @Mock
    lateinit var bookRepository: BookRepository
    @Mock
    lateinit var membersRepository: MembersRepository
    @Mock
    lateinit var rentalRepository: RentalRepository

    @Test
    fun 책을_정상적으로_대여하는경우() {
        //given
        val book = Book(
            title = "스프링부트 초보강의1",
            author = "hong",
            publishedDate = LocalDate.of(2020, 1, 2),
        )
        val member = Members(name = "제로코드")

        `when`(bookRepository.findById(1L)).thenReturn(Optional.of(book))
        `when`(membersRepository.findById(1L)).thenReturn(Optional.of(member))
        `when`(rentalRepository.existsByBookAndReturnDateIsNull(book)).thenReturn(false)
//        `when`(rentalRepository.findByIdAndReturnedDateIsNull(1L)).thenReturn(Optional.empty())

        //when
        rentalService.rentBook(RentalRequest(1L, 1L))

        //then
        verify(rentalRepository).save(any(Rental::class.java))
    }

    @Test
    fun 이미_대여된_책을_다시_대여_요청하는_경우() {
        //given
        val book = Book(
            title = "스프링부트 초보강의1",
            author = "hong",
            publishedDate = LocalDate.of(2020, 1, 2),
        )
        val member = Members(name = "제로코드")

        `when`(bookRepository.findById(1L)).thenReturn(Optional.of(book))
        `when`(membersRepository.findById(1L)).thenReturn(Optional.of(member))
        `when`(rentalRepository.existsByBookAndReturnDateIsNull(book)).thenReturn(true)

        assertThrows(AlreadyExistsException::class.java, {
            rentalService.rentBook(RentalRequest(1L, 1L)) }
        )
    }

    @Test
    fun 정상반응() {
        val book = Book(
            title = "스프링부트 초보강의1",
            author = "hong",
            publishedDate = LocalDate.of(2020, 1, 2),
        )
        val member = Members(name = "제로코드")
        val rental = Rental(members = member, book = book)

        `when`(rentalRepository.findByIdAndReturnedDateIsNull(1L)).thenReturn(Optional.of(rental))

        rentalService.returnBook(1L)

        assertNotNull(rental.returnDate)
    }

}