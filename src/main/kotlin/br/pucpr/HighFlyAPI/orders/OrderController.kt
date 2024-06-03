package br.pucpr.HighFlyAPI.orders

import br.pucpr.HighFlyAPI.orders.request.OrderRequest
import br.pucpr.HighFlyAPI.orders.response.OrderResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize

@RestController
@RequestMapping("/orders")
class OrderController(val orderService: OrderService) {

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "WebToken")
    @GetMapping()
    fun findAllOrders() = orderService.findAll()


    @GetMapping("/{id}")
    @SecurityRequirement(name = "WebToken")
    fun findByIdOrder(@PathVariable id: Long) =
        orderService.findByIdOrNull(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    @SecurityRequirement(name = "WebToken")
    fun insertOrder(@RequestBody @Valid orderReq: OrderRequest): ResponseEntity<OrderResponse> {
        return orderService.save(orderReq)
            .let { orderResp ->
                ResponseEntity.status(HttpStatus.CREATED)
                    .body(orderResp)
            }
    }


}