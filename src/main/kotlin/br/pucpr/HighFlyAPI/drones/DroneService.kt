package br.pucpr.HighFlyAPI.drones

import br.pucpr.HighFlyAPI.exceptions.BadRequestException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DroneService(val droneRepository: DroneRepository) {
    fun insert(drone: Drone): Drone = droneRepository.save(drone)

    fun findAll(): List<Drone>? = droneRepository.findAll()

    fun findByIdOrNull(id: Long): Drone? = droneRepository.findByIdOrNull(id)

    fun deleteDroneByID(id: Long) = droneRepository.deleteById(id)

    fun findUsage(id: Long): Boolean {
        val drone = droneRepository.findByIdOrNull(id) ?: throw BadRequestException("This Drone doesn't exists!")

        return drone.usage
    }

    fun changeUsage(id: Long, usingAlt: Boolean) = droneRepository.updateDroneStatus(id, usingAlt)
}