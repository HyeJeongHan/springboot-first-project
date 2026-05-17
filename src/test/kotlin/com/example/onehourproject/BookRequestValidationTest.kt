package com.example.onehourproject

import com.example.onehourproject.book.dto.BookRequest
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import java.time.LocalDate
import kotlin.test.Test

class BookRequestValidationTest {
    var validator: Validator? = null

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun 제목과_저자_출판일이_누락되면_에러메시지가_반환된다() {
        val bookRequest = BookRequest("", "", null)
        val violations: Set<ConstraintViolation<BookRequest>>? = validator?.validate(bookRequest)
        assertThat(violations).hasSize(3)
        assertThat(violations).anyMatch { it.message.equals("제목은 필수입니다.") }
        assertThat(violations).anyMatch { it.message.equals("저자는 필수입니다.") }
        assertThat(violations).anyMatch { it.message.equals("출판일은 필수입니다.") }
    }
}