package com.example.onehourproject.book

import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.entitis.Members
import com.example.onehourproject.book.entitis.Rental
import com.example.onehourproject.book.repositories.BookRepository
import com.example.onehourproject.book.repositories.MembersRepository
import com.example.onehourproject.book.repositories.RentalRepository
import net.datafaker.Faker
import org.springframework.boot.CommandLineRunner
import org.springframework.cglib.core.Local
import org.springframework.stereotype.Component
import java.util.Locale
import java.util.Random

@Component
class DummyDataGenerator(
    private val bookRepository: BookRepository,
    private val membersRepository: MembersRepository,
    private val rentalRepository: RentalRepository
) : CommandLineRunner {

    private val faker = Faker(Locale.KOREA)

    override fun run(vararg args: String?) {
        // 책
        val books = arrayListOf<Book>()
        for (i in 1..1000) {

            val book = Book(
                title = faker.book().title(),
                author = faker.book().author(),
                publishedDate = faker.timeAndDate().birthday()
            )

            books.add(book)
            bookRepository.saveAll(books)

        }

        //멤버
        val members = arrayListOf<Members>()
        for (i in 1..100) {
            val member = Members(
                name = faker.name().name()
            )

            members.add(member)
            membersRepository.saveAll(members)
        }


        // 대여
        val rentals = arrayListOf<Rental>()
        val random = Random()
        for (i in 1..100) {
            val rental = Rental(
                members = members[random.nextInt(members.size)],
                book = books[random.nextInt(books.size)]
            )

            rentals.add(rental)
            rentalRepository.saveAll(rentals)
        }


    }
}