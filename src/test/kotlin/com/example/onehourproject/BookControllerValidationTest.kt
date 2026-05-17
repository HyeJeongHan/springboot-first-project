package com.example.onehourproject

import com.example.onehourproject.book.controller.BookController
import com.example.onehourproject.book.dto.BookRequest
import com.example.onehourproject.book.repositories.BookRepository
import com.example.onehourproject.book.service.BookService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test


@WebMvcTest(BookController::class)
class BookControllerValidationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var bookService: BookService

    @MockitoBean
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun 유효하지않은_입력은_400에러와_필드별_메시지를_반환한다(){
        val bookRequest = BookRequest("", "", null)
        val json = objectMapper.writeValueAsString(bookRequest)

        mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        )
            ?.andExpect(status().isBadRequest)
            ?.andExpect(jsonPath("$.title").value("제목은 필수입니다."))
            ?.andExpect(jsonPath("$.author").value("저자는 필수입니다."))
            ?.andExpect(jsonPath("$.publishedDate").value("출판일은 필수입니다."))

    }
}
