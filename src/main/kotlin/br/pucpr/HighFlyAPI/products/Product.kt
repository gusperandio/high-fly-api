package br.pucpr.HighFlyAPI.products

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "products")
class Product(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String = "",

    @NotNull
    var price: Double = 0.0,

    @NotNull
    var amount: Int = 0
)