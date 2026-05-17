package com.example.onehourproject.book.controller

import com.example.onehourproject.book.dto.RentalRequest
import com.example.onehourproject.book.service.RentalService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rental")
class RentalController(private val rentalService: RentalService) {

    @PostMapping
    fun rental(@RequestBody rentalRequest: RentalRequest): ResponseEntity<Void> {
        rentalService.rentBook(rentalRequest)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{rentId}/return")
    fun returnBook(rentId: Long): ResponseEntity<Void> {
        rentalService.returnBook(rentId)
        return ResponseEntity.noContent().build()
    }
}