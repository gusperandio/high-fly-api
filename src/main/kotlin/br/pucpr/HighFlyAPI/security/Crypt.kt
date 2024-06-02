package br.pucpr.HighFlyAPI.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


class PasswordUtils {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun verifyPassword(rawPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(rawPassword, hashedPassword)
    }
}

fun hashPassword(password: String): String {
    val passwordEncoder = BCryptPasswordEncoder()
    return passwordEncoder.encode(password)
}