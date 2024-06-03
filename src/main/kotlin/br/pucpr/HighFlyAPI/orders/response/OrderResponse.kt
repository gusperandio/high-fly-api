package br.pucpr.HighFlyAPI.orders.response

import br.pucpr.HighFlyAPI.orders.Order
import java.text.DecimalFormat

data class OrderResponse(
    val order: Order,
    val totalPrice: Double,
    val time: String,
    val msgSupport: String
){
    constructor(u: Order, total: Double): this(
        order = u,
        totalPrice = DecimalFormat("#.##").format(total).replace(',', '.').toDouble(),
        time = "Your products come in 10 minutes",
        msgSupport = "If you need track order, the CODE is: ${u.identify}"
    )
}