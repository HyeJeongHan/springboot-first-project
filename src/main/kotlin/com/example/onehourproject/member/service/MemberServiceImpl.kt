package com.example.onehourproject.member.service

import com.example.onehourproject.member.repository.MemberRepository
import com.example.onehourproject.member.repository.entity.Member
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class MemberServiceImpl(
    private val memberRepository: MemberRepository
) : MemberService {
    override fun join(id: String, name: String, phoneNumber: String): String {
        val member = Member(index = 0, name = name, phoneNumber = phoneNumber)
        memberRepository.save(member)

        return "success"
    }
}