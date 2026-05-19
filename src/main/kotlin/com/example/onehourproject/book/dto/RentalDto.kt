package com.example.onehourproject.book.dto

import java.time.LocalDate

data class RentalDto(
    val id: Long,
    val title: String?,
    val memberName: String?,
    val rentedDate: LocalDate?,
    val returnedDate: LocalDate?,

)
