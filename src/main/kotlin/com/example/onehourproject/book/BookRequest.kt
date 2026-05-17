package com.example.onehourproject.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class BookRequest(
    @NotBlank(message = "{book.title.notblank}")
    val title: String,
    @NotBlank(message = "{book.author.notblank}")
    val author: String,
    @NotNull(message = "{book.publishedDate.notblank}")
    val publishedDate: LocalDate?,
)