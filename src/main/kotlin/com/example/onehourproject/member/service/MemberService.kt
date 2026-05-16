package com.example.onehourproject.member.service

interface MemberService {

    fun join(id: String, name: String, phoneNumber: String): String
}