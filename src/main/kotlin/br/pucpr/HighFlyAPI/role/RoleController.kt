package br.pucpr.HighFlyAPI.role

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/roles")
@RestController
class RoleController(val service: RoleService) {

    @PostMapping
    fun insert(@Valid @RequestBody role: RoleRequest) =
        service.insert(role.toRole())
            .let { ResponseEntity.ok(it) }

    @GetMapping
    fun list() = service.findAll()
        .let { ResponseEntity.ok(it) }
}