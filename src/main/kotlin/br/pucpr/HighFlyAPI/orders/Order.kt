package br.pucpr.HighFlyAPI.orders
import br.pucpr.HighFlyAPI.drones.Drone
import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.users.User
import java.time.LocalTime

class Order (
    var id: Long? = null,
    var idPerson: Long? = null,
    var products: List<Product>,
    var status: Boolean = false,
    var time: LocalTime = LocalTime.now(),
    var identify : String,
    var drone: Drone
)