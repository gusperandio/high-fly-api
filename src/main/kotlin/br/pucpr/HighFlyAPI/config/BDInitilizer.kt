package br.pucpr.HighFlyAPI.config

import br.pucpr.HighFlyAPI.drones.Drone
import br.pucpr.HighFlyAPI.drones.DroneRepository
import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.products.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BDInitilizer(private val productRepository: ProductRepository, private val droneRepository: DroneRepository) {

    @Bean
    fun initializeProducts(): CommandLineRunner {
        return CommandLineRunner {
            if(productRepository.count() == 0L){
                val products = listOf(
                    Product(name = "Flor Rara", price = 83.0, amount = 15),
                    Product(name = "Flor Lendária", price = 91.4, amount = 5),
                    Product(name = "Flor Semi Rara", price = 75.15, amount = 30),
                    Product(name = "Flor Simples", price = 39.99, amount = 342),
                    Product(name = "Flor Comum", price = 67.2, amount = 211)
                )
                productRepository.saveAll(products)
            }

            if(droneRepository.count() == 0L){
                val drones = listOf(
                    Drone(name = "DLR-1 MiniAircraft", range = 3.0, payload = 2.5),
                    Drone(name = "DLV-4.1 Aircraft", range = 8.0, payload = 6.0),
                    Drone(name = "DLV-Turbo Aircraft", range = 40.0, payload = 4.8)
                )

                droneRepository.saveAll(drones)
            }
        }
    }

}