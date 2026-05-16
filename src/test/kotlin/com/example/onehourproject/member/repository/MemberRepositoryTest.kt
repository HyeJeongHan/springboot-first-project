package com.example.onehourproject.member.repository

import com.example.onehourproject.member.repository.entity.Member
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberRepositoryTest {

    @Autowired private val memberRepository: MemberRepository? = null

    @Test
    fun crudTest() {
        val member = Member(id = 1, name = "레인", phoneNumber = "010-2222-3333")
//        memberRepository?.save(member)
        val foundMember = memberRepository?.findById(1)?.get()
    }

}