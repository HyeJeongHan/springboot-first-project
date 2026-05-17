package com.example.onehourproject.book.dto

import jakarta.validation.constraints.NotBlank

data class RentalRequest(
    @NotBlank(message = "rental.memberId.notblank")
    val memberId: Long,
    @NotBlank(message = "rental.bookId.notblank")
    val bookId: Long,
)