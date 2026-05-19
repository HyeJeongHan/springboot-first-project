package com.example.onehourproject.book.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(@Value("\${jwt.secret}") secret: String) {

    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    private val jwtParser = Jwts.parser().verifyWith(key).build()
    private val validityInMilliseconds: Long = 3600000

    fun createToken(username: String, roles: List<String>): String {
        val now = Instant.now()
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusMillis(validityInMilliseconds)))
            .claim("roles", roles)
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return runCatching { jwtParser.parseSignedClaims(token) }.isSuccess
    }

    fun getAuthentication(token: String): Authentication {
        val claims: Claims = jwtParser.parseSignedClaims(token).payload
        val username = claims.subject
        val roles = claims.get("roles", List::class.java)
        val authorities = roles.map { SimpleGrantedAuthority(it.toString()) }
        val userDetails = User(username, "", authorities)
        return UsernamePasswordAuthenticationToken(userDetails, token, authorities)
    }
}
