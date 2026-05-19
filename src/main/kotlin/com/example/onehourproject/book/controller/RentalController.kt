package com.example.onehourproject.book.controller

import com.example.onehourproject.book.dto.RentalDto
import com.example.onehourproject.book.dto.RentalRequest
import com.example.onehourproject.book.dto.RentalResponse
import com.example.onehourproject.book.service.RentalService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rental")
class RentalController(private val rentalService: RentalService) {

    @GetMapping
    fun getRentals(pageable: Pageable): Page<RentalDto> {
        return rentalService.getRentals(pageable)
    }

    @PostMapping
    fun rental(@RequestBody rentalRequest: RentalRequest): ResponseEntity<RentalResponse> {
        val response = rentalService.rentBook(rentalRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PatchMapping("/{rentId}/return")
    fun returnBook(rentId: Long): ResponseEntity<Void> {
        rentalService.returnBook(rentId)
        return ResponseEntity.noContent().build()
    }
}