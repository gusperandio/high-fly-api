package br.pucpr.HighFlyAPI.users

import br.pucpr.HighFlyAPI.enums.SortDir
import br.pucpr.HighFlyAPI.role.RoleRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository
) {
    fun save(user: User): User {
        val firstUser = userRepository.count() == 0L
        if (firstUser) {
            val roleName = "ADMIN"
            val i = roleRepository.findByName(roleName)

            if (i != null)
                user.roles.add(i)
        }

        return userRepository.save(user)
    }

    fun findAll(dir: SortDir, role: String?) =
        role?.let { r ->
            when (dir) {
                SortDir.ASC -> userRepository.findByRole(r).sortedBy { it.name }
                SortDir.DESC -> userRepository.findByRole(r).sortedByDescending { it.name }
            }

        } ?: when (dir) {
            SortDir.ASC -> userRepository.findAll(Sort.by("name").ascending())
            SortDir.DESC -> userRepository.findAll(Sort.by("name").descending())
        }


    fun findByIdOrNull(id: Long) = userRepository.findByIdOrNull(id)

    fun deleteById(id: Long) = userRepository.deleteById(id)

    fun addRole(id: Long, roleName: String): Boolean {
        val user = userRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("User not found with id $id")

        if (user.roles.any { it.name == roleName }) return false

        val i = roleRepository.findByName(roleName)
            ?: throw IllegalArgumentException("Role with name $roleName not found")

        user.roles.add(i)
        userRepository.save(user)
        return true
    }
}