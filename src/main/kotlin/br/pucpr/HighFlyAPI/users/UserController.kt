package br.pucpr.HighFlyAPI.users

import jakarta.validation.Valid
import jakarta.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @PostMapping()
    fun insertUser(@RequestBody @Valid userReq: UserRequest): ResponseEntity<User> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.save(userReq.toUser()))

    @GetMapping()
    fun findAll(sortDir: String?) =
        SortDir.entries.firstOrNull { it.name == (sortDir ?: "ASC").uppercase() }
            ?.let { userService.findAll(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/{id}")
    fun findByIdRoute(@PathVariable id: Long) =
        userService.findByIdOrNull(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> =
        userService.deleteById(id)
            ?.let { ResponseEntity.ok().build() }
            ?: ResponseEntity.notFound().build()
}