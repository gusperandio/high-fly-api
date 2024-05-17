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
    fun insertRoute(@RequestBody @Valid userReq: UserRequest): ResponseEntity<User> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(userReq.toUser())

    @GetMapping()
    fun findAllRoute() = userService.findAll()

    @GetMapping("/{id}")
    fun findByIdRoute(@PathVariable id: Long) =
        userService.findByIdOrNull(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()


}