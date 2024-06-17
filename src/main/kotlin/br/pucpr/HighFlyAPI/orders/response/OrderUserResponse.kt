package br.pucpr.HighFlyAPI.orders.response

import br.pucpr.HighFlyAPI.drones.Drone
import br.pucpr.HighFlyAPI.orders.Order
import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.users.responses.UserResponse
import java.text.DecimalFormat


data class OrderUserResponse(
    val user: UserResponse,
    val drone: Drone?,
    val status: Boolean,
    val time: String,
    val identify: String,
    val products : MutableSet<Product>
){
    constructor(u: Order): this(
      user = UserResponse(u.userOrder!!),
        drone = u.drone,
        status = u.status,
        time = u.time.toString(),
        identify = u.identify,
       products = u.products   
    )
}