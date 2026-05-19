package com.example.onehourproject.book.entitis

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.io.Serializable
import java.time.LocalDate

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String = "",
    var author: String = "",
    var publishedDate: LocalDate? = null
): Serializable
