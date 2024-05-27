package br.pucpr.HighFlyAPI.orders

import br.pucpr.HighFlyAPI.products.Product
import br.pucpr.HighFlyAPI.products.ProductRepository
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

//data class OrderRequest(
//    @NotBlank
//    val idPerson: Long,
//    val products: List<Long>,
//){
//    fun toOrder(): Order{
//        val product = ProductRepository;
//        return Order(
//            idPerson = idPerson!!,
//            products = product.findMultipleIdsOrNull(products)!!,
//        )
//    }
//}
