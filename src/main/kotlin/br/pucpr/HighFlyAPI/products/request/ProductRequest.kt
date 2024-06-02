package br.pucpr.HighFlyAPI.products.request

import br.pucpr.HighFlyAPI.products.Product
import jakarta.validation.constraints.NotBlank

data class ProductRequest(
    @NotBlank
    val name: String,
    val price: Double,
    val amount: Int,
    val weight: Double,
) {
    fun toProduct() = Product(
        name = name,
        price = price,
        amount = amount,
        weight = weight,
    )
}