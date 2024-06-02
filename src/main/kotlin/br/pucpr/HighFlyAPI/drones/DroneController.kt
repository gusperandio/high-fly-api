package br.pucpr.HighFlyAPI.drones

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/drones")
class DroneController(val droneService: DroneService) {

    @PostMapping()
    fun insertDrone(@RequestBody @Valid droneReq: DroneRequest): ResponseEntity<Drone> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(droneService.insert(droneReq.toDrone()))

    @GetMapping()
    fun findAll() = droneService.findAll()
        ?.map { DroneResponse(it) }
        ?.let { ResponseEntity.ok(it) }
        ?: ResponseEntity.notFound().build()

    @GetMapping("/{id}")
    fun findByIdOrNull(@PathVariable id: Long) =
        droneService.findByIdOrNull(id)
            ?.let { DroneResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> =
        droneService.deleteDroneByID(id)
            .let { ResponseEntity.ok().build() }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/status/{id}")
    fun findStatus(@PathVariable id: Long): ResponseEntity<Boolean> =
        droneService.findUsage(id).let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()


}