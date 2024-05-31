package br.pucpr.HighFlyAPI.drones

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "drones")
class Drone (
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String? = null,

    @NotNull
    var range: Double? = null,

    @NotNull
    var payload: Double? = null,

    @NotNull
    var using: Boolean = false
)