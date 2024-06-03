package br.pucpr.HighFlyAPI.products

import br.pucpr.HighFlyAPI.products.request.ProductRequest
import br.pucpr.HighFlyAPI.utils.determineOrder
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/procuts")
class ProductController(val productService: ProductService) {

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "WebToken")
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
    fun findById(@PathVariable id: Long) =
        productService.findProductById(id)
            ?.let { ProductResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
}