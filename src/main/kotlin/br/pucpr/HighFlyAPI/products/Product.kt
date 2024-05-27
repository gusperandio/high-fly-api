package br.pucpr.HighFlyAPI.products

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull

class Product (

    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String = "",

    @NotNull
    var price: Double = 0.0,

    @NotNull
    var amount: Int = 0
)