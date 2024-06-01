package br.pucpr.HighFlyAPI.products

import br.pucpr.HighFlyAPI.enums.SortDir
import br.pucpr.HighFlyAPI.enums.TypeOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired val productRepository: ProductRepository) {
    fun findAll(order : TypeOrder): List<Product> = order.let { r ->
        when (r) {
            TypeOrder.NAME -> productRepository.findAll(Sort.by("name").ascending())
            TypeOrder.PRICE -> productRepository.findAll(Sort.by("price").ascending())
        }
    }

    fun insert(product: Product): Product = productRepository.save(product)

    fun findProductById(id: Long): Product? = productRepository.findByIdOrNull(id)
}