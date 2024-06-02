package br.pucpr.HighFlyAPI.orders

import br.pucpr.HighFlyAPI.drones.Drone
import br.pucpr.HighFlyAPI.drones.DroneRepository
import br.pucpr.HighFlyAPI.exceptions.BadRequestException
import br.pucpr.HighFlyAPI.orders.request.OrderRequest
import br.pucpr.HighFlyAPI.orders.response.OrderResponse
import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.products.ProductRepository
import br.pucpr.HighFlyAPI.users.User
import br.pucpr.HighFlyAPI.users.UserRepository
import br.pucpr.HighFlyAPI.utils.balance
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val droneRepository: DroneRepository,
    val productRepository: ProductRepository,
    val userRepository: UserRepository
) {
    fun save(order: OrderRequest): OrderResponse {
        val user: User = userRepository.findByIdOrNull(order.userId) ?: throw BadRequestException("User does not exist")

        if (orderRepository.existsOrderToFinish(user.id!!)) throw BadRequestException("You need finish an open Order or delete the old Order")

        val drone: Drone = droneRepository.findByIdOrNull(order.droneId) ?: throw BadRequestException("Drone does not exist")

        if (drone.usage) throw BadRequestException("The drone you chose is busy!")

        val (price, balanceCondition) = balance(order.products, drone)
        if (!balanceCondition) {
            droneRepository.updateDroneStatus(drone.id!!, false) // Atualiza o status do drone para disponível
            throw BadRequestException("The selected drone does not have enough capacity to transport the products!")
        }

        droneRepository.updateDroneStatus(drone.id!!, true)

        val productsMap = mutableMapOf<String, Int>()
        for (product in order.products) {
            productsMap[product.name] = product.amount
        }

        for ((name, quantity) in productsMap) {
            productRepository.removeAmounts(name, quantity)
        }

        return OrderResponse(orderRepository.save(
            Order(
                user = user,
                drone = drone,
                products = order.products.toMutableSet(),
            )
        ), price)
    }

    fun findAll(): List<Order>? = orderRepository.findAll()

    fun findByIdOrNull(id: Long) = orderRepository.findByIdOrNull(id)

    fun finishOrder(id: Long): String =
        if (orderRepository.updateOrderStatus(id, true) == 1)
            "Purchase completed"
        else
            throw BadRequestException("We have an error to finish your acquisition.")
}