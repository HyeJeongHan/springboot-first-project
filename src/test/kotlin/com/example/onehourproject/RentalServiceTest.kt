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
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDate
import kotlin.test.Test


@DataJpaTest
internal class RentalServiceTest {
    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var memberRepository: MembersRepository

    @Autowired
    private lateinit var rentalRepository: RentalRepository

    @Test
    fun 대여중인_도서는_대여할_수_없다() {
        val service = RentalService(bookRepository, memberRepository, rentalRepository!!)

        val book: Book = bookRepository.save(Book(title = "스트핑부트 초보강의", author = "코드제로", publishedDate = LocalDate.of(2025, 4, 19)))
        val member: Members = memberRepository.save(Members(name = "코드제로"))

        rentalRepository.save(Rental(members = member, book = book))


        assertThatThrownBy({
            book.id?.let { it ->
                member.id?.let { it1 ->
                    service.rentBook(RentalRequest(it1, it))
                }
            }
        })
            .isInstanceOf(AlreadyExistsException::class.java)
            .hasMessage("이미 대여 중입니다.")
    }
}