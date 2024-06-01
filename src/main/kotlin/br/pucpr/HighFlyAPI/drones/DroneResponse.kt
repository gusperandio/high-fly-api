package br.pucpr.HighFlyAPI.drones

import br.pucpr.HighFlyAPI.drones.Drone

data class DroneResponse(
    val name: String,
    val range: Double?,
    val payload: Double?,
    val usage: Boolean
) {
    constructor(d: Drone) : this(
        name = d.name!!,
        range = d.range,
        payload = d.payload,
        usage = d.usage
    )
}