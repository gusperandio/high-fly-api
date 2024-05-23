package br.pucpr.HighFlyAPI.orders
import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.users.User

class Order (
    var id: Long? = null,
    var idPerson: Long? = null,
    var products: List<Product>,
)