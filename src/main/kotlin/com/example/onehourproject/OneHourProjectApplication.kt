package com.example.onehourproject

import com.example.onehourproject.book.entitis.Book
import com.example.onehourproject.book.repositories.BookRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDate
import kotlin.collections.forEach

@SpringBootApplication
class OneHourProjectApplication {

    @Bean
    fun runner(bookRepository: BookRepository) = CommandLineRunner {
        bookRepository.save(
            Book(
                title = "Spring Boot 시작하기",
                author = "hong",
                publishedDate = LocalDate.of(2025, 1, 2)
            )
        )
        bookRepository.save(
            Book(
                title = "스프링부트 완전정복",
                author = "lee",
                publishedDate = LocalDate.of(2025, 4, 7))
        )
        bookRepository.save(
            Book(
                title = "스프링 고급",
                author = "han",
                publishedDate = LocalDate.of(2025, 7, 11))
        )
        println("[전체조회]")
        bookRepository.findAll().forEach { book ->
            println(book.title)
        }
        println("[작가로 조회]")
        bookRepository.findByAuthor("hong").forEach { book ->
            println("${book.title} / ${book.author} / ${book.publishedDate}")
        }
        println("[작가와 출판일로 조회]")
        bookRepository.findByAuthorAndPublishedDate("lee", LocalDate.of(2025, 4, 7)).forEach { book ->
            println("${book.title} / ${book.author} / ${book.publishedDate}")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<OneHourProjectApplication>(*args)
}
