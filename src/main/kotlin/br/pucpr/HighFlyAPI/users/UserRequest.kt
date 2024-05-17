package br.pucpr.HighFlyAPI.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRequest(
    @field:Email
    val email: String?,
    @NotBlank
    val password: String?,
    val name: String?,
){
    fun toUser() = User(email = email!!,
        password = password!!,
        name = name ?: "")
}
