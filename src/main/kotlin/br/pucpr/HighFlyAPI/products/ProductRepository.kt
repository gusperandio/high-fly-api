package br.pucpr.HighFlyAPI.products

import br.pucpr.HighFlyAPI.users.User
import org.springframework.stereotype.Component

@Component
object ProductRepository {
    val products: MutableMap<Long, Product> = mutableMapOf()

    init {
        products[1L] = Product(id = 1L, name = "Flor Rara", price = 83.0, amount = 15)
        products[2L] = Product(id = 2L, name = "Flor Lendária", price = 91.4, amount = 5)
        products[3L] = Product(id = 3L, name = "Flor Semi Rara", price = 75.15, amount = 30)
        products[4L] = Product(id = 4L, name = "Flor Simples", price = 39.99, amount = 342)
        products[5L] = Product(id = 6L, name = "Flor Comum", price = 67.2, amount = 211)
    }

    fun findAll(): List<Product> {
        return products.values.toList();
    }

    fun findByIdOrNull(id: Long): Product? {
        return products[id]
    }

    fun findMultipleIdsOrNull(ids: List<Long>): List<Product>? {
        return ids.mapNotNull { products[it] }
    }


}