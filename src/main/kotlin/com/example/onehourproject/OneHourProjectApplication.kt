package com.example.onehourproject

import com.example.onehourproject.book.Book
import com.example.onehourproject.book.BookRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import kotlin.collections.forEach

@SpringBootApplication
class OneHourProjectApplication {

    @Bean
    fun runner(bookRepository: BookRepository) = CommandLineRunner {
        bookRepository.save(Book("Spring Boot 시작하기"))
        bookRepository.save(Book("스프링부트 완전정복"))
        bookRepository.findAll().forEach { book ->
            println(book.title)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<OneHourProjectApplication>(*args)
}
