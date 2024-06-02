package br.pucpr.HighFlyAPI.utils

import br.pucpr.HighFlyAPI.drones.Drone
import br.pucpr.HighFlyAPI.products.Product

fun balance(products: List<Product>, drone: Drone): Pair<Double, Boolean> {

    var maxWeight = 0.0
    var totalPrice = 0.0

    for (product in products) {
        val totalWeight = product.weight!! * product.amount
        maxWeight += totalWeight
        totalPrice += product.amount * product.price!!
    }

    return Pair(totalPrice, (drone.payload!! < maxWeight))
}