package com.example.onehourproject.member.controller.dto

import lombok.Data

@Data
data class JoinRequest (
    val id: String,
    val name: String,
    val phoneNumber: String,
)