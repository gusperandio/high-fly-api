package br.pucpr.HighFlyAPI.role

import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long>{
    fun findByName(name: String): Role?
}