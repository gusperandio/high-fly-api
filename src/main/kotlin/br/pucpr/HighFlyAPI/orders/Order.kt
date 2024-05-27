package br.pucpr.HighFlyAPI.orders
import br.pucpr.HighFlyAPI.drones.Drone
import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.users.User
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull
import java.time.LocalTime

class Order (

    @Id
    @GeneratedValue
    var id: Long? = null,

    @NotNull
    var idPerson: Long? = null,

    @NotNull
    var products: List<Product>,

    @NotNull
    var status: Boolean = false,

    @NotNull
    var time: LocalTime = LocalTime.now(),

    @NotNull
    var identify : String,

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id")
    var drone: Drone

)