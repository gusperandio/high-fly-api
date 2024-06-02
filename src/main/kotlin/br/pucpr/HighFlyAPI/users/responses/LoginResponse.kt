package br.pucpr.HighFlyAPI.users.responses

data class LoginResponse(
    val access_token: String,
    val user: UserResponse
)