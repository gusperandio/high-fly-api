package br.pucpr.HighFlyAPI.drones

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull

class Drone (

    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String? = null,

    @NotNull
    var range: Double? = null,

    @NotNull
    var payload: Double? = null,
)