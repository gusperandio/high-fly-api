package br.pucpr.HighFlyAPI.users.requests

data class LoginRequest(
    val email: String?,
    val password: String?
)