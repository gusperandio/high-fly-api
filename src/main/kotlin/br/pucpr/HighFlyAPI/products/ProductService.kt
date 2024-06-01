package br.pucpr.HighFlyAPI.products

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired val productRepository: ProductRepository) {
    fun findAll(): List<Product> = productRepository.findAll()

    fun insert(product: Product): Product = productRepository.save(product)

    fun findProductById(id: Long): Product? = productRepository.findByIdOrNull(id)
}