package br.pucpr.HighFlyAPI.orders

import br.pucpr.HighFlyAPI.drones.DroneRepository
import br.pucpr.HighFlyAPI.products.ProductRepository
import br.pucpr.HighFlyAPI.users.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepository: OrderRepository,
                   val droneRepository: DroneRepository,
                    val productRepository: ProductRepository,
                    val userRepository: UserRepository
) {
    fun save(order: Order) = orderRepository.save(order)

    fun findAll(): List<Order>? = orderRepository.findAll()

    fun findByIdOrNull(id: Long) = orderRepository.findByIdOrNull(id)
}