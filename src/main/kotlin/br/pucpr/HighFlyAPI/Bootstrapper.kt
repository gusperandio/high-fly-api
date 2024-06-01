package br.pucpr.HighFlyAPI

import br.pucpr.HighFlyAPI.drones.Drone
import br.pucpr.HighFlyAPI.drones.DroneRepository
import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.products.ProductRepository
import br.pucpr.HighFlyAPI.role.Role
import br.pucpr.HighFlyAPI.role.RoleRepository
import br.pucpr.HighFlyAPI.users.User
import br.pucpr.HighFlyAPI.users.UserRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val droneRepository: DroneRepository,
    val productRepository: ProductRepository
): ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole = roleRepository.findByName("ADMIN")
            ?: roleRepository.save(Role(name = "ADMIN", description = "System administration"))
                .also { roleRepository.save(Role(name = "USER", description = "Premium User")) }

        if (userRepository.findByRole(adminRole.name).isEmpty()) {
            val admin = User(
                name = "Auth Server Administrator",
                email = "admin@authserver.com",
                password = "admin")

            admin.roles.add(adminRole)

            userRepository.save(admin)
        }

        if(productRepository.count() == 0L){
            val products = listOf(
                Product(name = "Flor Rara", price = 83.0, amount = 15, weight = 0.3),
                Product(name = "Flor Lendária", price = 91.4, amount = 5, weight = 1.1),
                Product(name = "Flor Semi Rara", price = 75.15, amount = 30, weight = 0.72),
                Product(name = "Flor Simples", price = 39.99, amount = 342, weight = .60),
                Product(name = "Flor Comum", price = 67.2, amount = 211, weight = 0.26 )
            )
            productRepository.saveAll(products)
        }

        if(droneRepository.count() == 0L){
            val drones = listOf(
                Drone(name = "DLR-1 MiniAircraft", range = 3.0, payload = 2.5, usage = false),
                Drone(name = "DLV-4.1 Aircraft", range = 8.0, payload = 6.0, usage = false),
                Drone(name = "DLV-Turbo Aircraft", range = 40.0, payload = 4.8, usage = false)
            )

            droneRepository.saveAll(drones)
        }


    }

}