package br.pucpr.HighFlyAPI.products


data class ProductResponse(
    val productId: Long?,
    val name: String,
    val price: Double,
    val amount: Int,
    val weight: Double,
) {
    constructor(p: Product) : this(
        productId = p.id,
        name = p.name,
        price = p.price!!,
        amount = p.amount,
        weight = p.weight!!
    )
}