package br.pucpr.HighFlyAPI.users

import jakarta.persistence.Entity

@Entity
class User (
    var id: Long? = null,
    var email: String = "",
    var password: String = "",
    var name: String = ""
)