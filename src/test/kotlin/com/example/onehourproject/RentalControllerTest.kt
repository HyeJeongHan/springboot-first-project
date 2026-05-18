package com.example.onehourproject

import com.example.onehourproject.book.controller.RentalController
import com.example.onehourproject.book.dto.RentalRequest
import com.example.onehourproject.book.dto.RentalResponse
import com.example.onehourproject.book.repositories.BookRepository
import com.example.onehourproject.book.repositories.MembersRepository
import com.example.onehourproject.book.repositories.RentalRepository
import com.example.onehourproject.book.service.RentalService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@WebMvcTest(RentalController::class)
class RentalControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var rentalService: RentalService
    @MockitoBean
    private lateinit var bookRepository: BookRepository
    @MockitoBean
    private lateinit var membersRepository: MembersRepository
    @MockitoBean
    private lateinit var rentalRepository: RentalRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun 책_대여() {
        val request = RentalRequest(1L, 1L)
        `when`(rentalService.rentBook(RentalRequest(1L, 1L)))
            .thenReturn(RentalResponse(1L,"스프링부트 기초강의", "코드제로", LocalDate.now()))

        mockMvc.perform(post("/rental")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isCreated)
            .andExpect(jsonPath("$.rentalId").value(1L))
            .andExpect(jsonPath("$.bookTitle").value("스프링부트 기초강의"))

    }
}