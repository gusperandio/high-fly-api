package br.pucpr.HighFlyAPI.users

import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {
    fun save(user: User) = userRepository.save(user)

    fun findAll(dir: SortDir) =
        if(dir == SortDir.ASC) userRepository.findAll().sortedBy { it.name }
        else userRepository.findAll().sortedByDescending { it.name }

    fun findByIdOrNull(id: Long) = userRepository.findByIdOrNull(id)

    fun deleteById(id: Long) = userRepository.delete(id)
}