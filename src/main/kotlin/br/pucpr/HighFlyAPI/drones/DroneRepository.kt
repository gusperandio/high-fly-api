package br.pucpr.HighFlyAPI.drones

import br.pucpr.HighFlyAPI.enums.Info
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DroneRepository : JpaRepository<Drone, Long>{

    @Modifying
    @Transactional
    @Query("UPDATE Drone d SET d.using = :status WHERE d.id = :id")
    fun updateDroneStatus(@Param("id") id: Long, @Param("status") status: Boolean): Int

    @Query("SELECT d.using FROM Drone d WHERE d.id = :id")
    fun findStatusById(@Param("id") id: Long): Boolean

}

//    return when (typeInfo) {
//        Info.RANGE -> drone.range
//        Info.PAYLOAD -> drone.payload
//    }