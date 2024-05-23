package br.pucpr.HighFlyAPI.products

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired val productRepository: ProductRepository) {
    fun findAll() = productRepository.findAll()

    fun getProductById(id: Long) = productRepository.findByIdOrNull(id)
}