package com.example.onehourproject.member.controller

import com.example.onehourproject.member.controller.dto.JoinRequest
import com.example.onehourproject.member.service.MemberService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class MemberController(private val memberService: MemberService) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello Spring Boot!"
    }

    @PostMapping("/join")
    fun join(@RequestBody joinRequest: JoinRequest): String {
        val id = joinRequest.id
        val name = joinRequest.name
        val phoneNumber = joinRequest.phoneNumber

        val result = memberService.join(id, name, phoneNumber)

        return if (result == "success") {
            "success"
        } else {
            "fail"
        }
    }
}