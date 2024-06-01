package br.pucpr.HighFlyAPI.drones

import jakarta.validation.constraints.NotBlank

data class DroneRequest(
    @NotBlank
    val name: String,
    val range: Double,
    val payload: Double,
) {
    fun toDrone() = Drone(
        name = name,
        range = range,
        payload = payload,
    )
}