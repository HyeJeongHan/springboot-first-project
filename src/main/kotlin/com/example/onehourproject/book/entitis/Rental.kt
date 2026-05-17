package com.example.onehourproject.book.entitis

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
class Rental(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var members: Members? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    var book: Book? = null,
    var rentedDate: LocalDate = LocalDate.now(),
    var returnDate: LocalDate? = null
)
