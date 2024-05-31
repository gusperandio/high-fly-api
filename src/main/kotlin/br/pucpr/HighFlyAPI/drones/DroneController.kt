package br.pucpr.HighFlyAPI.drones

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drones")
class DroneController(val droneService: DroneService) {

    @GetMapping("/{id}")
    fun findByIdOrNull(@PathVariable id: Long) =
        droneService.findByIdOrNull(id)
            ?.let {ResponseEntity.ok(it)}
            ?: ResponseEntity.notFound().build()
}