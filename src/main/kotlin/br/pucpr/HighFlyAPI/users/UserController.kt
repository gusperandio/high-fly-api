package br.pucpr.HighFlyAPI.users

import br.pucpr.HighFlyAPI.enums.SortDir
import br.pucpr.HighFlyAPI.users.requests.LoginRequest
import br.pucpr.HighFlyAPI.users.requests.UserRequest
import br.pucpr.HighFlyAPI.users.responses.UserResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @PostMapping
    fun insertUser(@RequestBody @Valid userReq: UserRequest): ResponseEntity<UserResponse> {
        return userService.save(userReq.toUser())
            .let { userResponse ->
                ResponseEntity.status(HttpStatus.CREATED)
                    .body(UserResponse(userResponse))
            }
    }


    @GetMapping()
    fun findAll(
        @RequestParam sortDir: String? = null,
        @RequestParam role: String? = null
    ) =
        SortDir.entries.firstOrNull { it.name == (sortDir ?: "ASC").uppercase() }
            ?.let { userService.findAll(it, role) }
            ?.map { UserResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/{id}")
    fun findByIdRoute(@PathVariable id: Long) =
        userService.findByIdOrNull(id)
            ?.let { UserResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> =
        userService.deleteById(id)
            .let { ResponseEntity.ok().build() }
            ?: ResponseEntity.notFound().build()

    @PutMapping("/{id}/roles/{role}")
    fun grantRole(
        @PathVariable id: Long,
        @PathVariable role: String
    ): ResponseEntity<Void> =
        if (userService.addRole(id, role)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()

    @PostMapping("/login")
    fun login(@Valid @RequestBody login: LoginRequest) =
        userService.login(login.email!!, login.password!!)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

}