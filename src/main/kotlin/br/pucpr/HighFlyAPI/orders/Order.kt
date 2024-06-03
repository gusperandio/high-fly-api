package br.pucpr.HighFlyAPI.orders
import br.pucpr.HighFlyAPI.drones.Drone
import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.users.User
import br.pucpr.HighFlyAPI.utils.generateGuid
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.LocalTime

@Entity
@Table(name = "orders")
class Order (

    @Id
    @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    @JoinTable(
        name = "orderUser",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var userOrder: User? = null ,

    @ManyToOne
    @JoinColumn(name = "drone_id")
    var drone: Drone? = null,

    @NotNull
    var status: Boolean = false,

    @NotNull
    var time: LocalTime = LocalTime.now(),

    @NotNull
    var identify : String = generateGuid(true).uppercase(),

    @ManyToMany
    @JoinTable(
        name = "order_product",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    var products: MutableSet<Product> = mutableSetOf()
)