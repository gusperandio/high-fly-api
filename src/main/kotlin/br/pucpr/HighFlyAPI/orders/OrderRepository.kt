package br.pucpr.HighFlyAPI.orders

import br.pucpr.HighFlyAPI.users.User
import org.springframework.stereotype.Component


@Component
class OrderRepository {
    private var lastId = 0L;
    private val orders =
        mutableMapOf<Long, Order>()

    fun save(order: Order) : Order {
        val id = order.id

        if(id == null){
            lastId += 1
            order.id = lastId
            orders[lastId] = order
        }else{
            orders[id] = order
        }

        return order
    }

    fun findAllOrders(): List<Order>{
        return orders.values.toList()
    }

    fun findByIdOrNull(id: Long) = orders[id]
}