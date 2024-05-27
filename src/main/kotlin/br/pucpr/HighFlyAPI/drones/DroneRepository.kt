package br.pucpr.HighFlyAPI.drones

object DroneRepository {
    val drones: MutableMap<Long, Drone> = mutableMapOf()

    init {
        drones[1L] = Drone(1L, name = "DLR-1 MiniAircraft", range = 3.0, payload = 2.5)
        drones[2L] = Drone(2L, name = "DLV-4.1 Aircraft", range = 8.0, payload = 6.0)
        drones[3L] = Drone(3L, name = "DLV-Turbo Aircraft", range = 40.0, payload = 4.8)
    }

    fun findAll(): List<Drone> = drones.values.toList()

    fun findById(id: Long): Drone? = drones[id]

    fun findDroneSpecifications(id: Long, typeInfo: Info): Double? {
        val drone = drones[id] ?: return 0.0

        return when (typeInfo) {
            Info.RANGE -> drone.range
            Info.PAYLOAD -> drone.payload
        }
    }


}