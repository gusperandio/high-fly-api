package br.pucpr.HighFlyAPI.orders.request

import br.pucpr.HighFlyAPI.products.Product

data class OrderRequest(
    val userId: Long,
    val droneId: Long,
    val products: List<Product>,
)
