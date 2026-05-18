package com.example.onehourproject.book.dto

import java.time.LocalDate

data class RentalResponse(
    val rentalId: Long?,
    val bookTitle: String?,
    val memberName: String?,
    val rentedDate: LocalDate?,
)
