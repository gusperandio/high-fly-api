package br.pucpr.HighFlyAPI.users

import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {
    fun save(user: User) = userRepository.save(user)

    fun findAll() = userRepository.findAll()

    fun findByIdOrNull(id: Long) = userRepository.findByIdOrNull(id)
}