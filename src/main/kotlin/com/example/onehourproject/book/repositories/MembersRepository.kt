package com.example.onehourproject.book.repositories

import com.example.onehourproject.book.entitis.Members
import org.springframework.data.jpa.repository.JpaRepository

interface MembersRepository : JpaRepository<Members, Long>
