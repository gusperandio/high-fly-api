package br.pucpr.HighFlyAPI.orders

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepository: OrderRepository) {
    fun save(order: Order) = orderRepository.save(order)

    fun findAll(): List<Order>? = orderRepository.findAll()

    fun findByIdOrNull(id: Long) = orderRepository.findByIdOrNull(id)
}