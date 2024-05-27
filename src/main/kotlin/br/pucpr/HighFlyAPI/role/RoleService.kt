package br.pucpr.HighFlyAPI.role

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class RoleService(val repository: RoleRepository) {
    fun insert(role: Role): Role  = repository.save(role)

    fun findAll(): List<Role>? = repository.findAll(Sort.by("name").ascending())
}