package br.pucpr.HighFlyAPI.products


import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/procuts")
class ProductController(val productService: ProductService) {

    @GetMapping
    fun findAllProducts(): ResponseEntity<List<Product>> =
        productService.findAll()
            .let { ResponseEntity.ok(it) }


}