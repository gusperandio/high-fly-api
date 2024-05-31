package br.pucpr.HighFlyAPI.drones

import br.pucpr.HighFlyAPI.enums.Info
import org.springframework.data.jpa.repository.JpaRepository

interface DroneRepository : JpaRepository<Drone, Long>
 
//    return when (typeInfo) {
//        Info.RANGE -> drone.range
//        Info.PAYLOAD -> drone.payload
//    }