package br.pucpr.HighFlyAPI.drones

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DroneService(val repository: DroneRepository) {
    fun insert(drone: Drone): Drone = repository.save(drone)

    fun findAll(): List<Drone>? = repository.findAll()

    fun findByIdOrNull(id: Long): Drone? = repository.findByIdOrNull(id)

    fun deleteDroneByID(id: Long) = repository.deleteById(id)

    fun findUsage(id: Long): Boolean = repository.findStatusById(id)

    fun changeUsage(id: Long, usingAlt: Boolean) = repository.updateDroneStatus(id, usingAlt)
}