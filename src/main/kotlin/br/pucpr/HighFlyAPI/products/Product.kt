package br.pucpr.HighFlyAPI.products

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "products")
class Product (
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val name: String,

    @NotNull
    var price: Double = 0.0,

    @NotNull
    var amount: Int = 0
)