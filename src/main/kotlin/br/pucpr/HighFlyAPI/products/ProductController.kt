package br.pucpr.HighFlyAPI.products

import br.pucpr.HighFlyAPI.utils.determineOrder
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/procuts")
class ProductController(val productService: ProductService) {

    @PostMapping()
    fun insertProduct(@RequestBody @Valid prodReq: ProductRequest): ResponseEntity<Product> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(productService.insert(prodReq.toProduct()))


    @GetMapping
    fun findAllProducts(
        @RequestParam name: Boolean? = false,
        @RequestParam price: Boolean? = false
    ) =
        determineOrder(name, price)
            .let { productService.findAll(it) }
            .map { ProductResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun findByIdRoute(@PathVariable id: Long) =
        productService.findProductById(id)
            ?.let { ProductResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
}