package br.pucpr.HighFlyAPI.users

import br.pucpr.HighFlyAPI.enums.SortDir
import br.pucpr.HighFlyAPI.role.RoleRepository
import br.pucpr.HighFlyAPI.security.Jwt
import br.pucpr.HighFlyAPI.security.PasswordUtils
import br.pucpr.HighFlyAPI.security.hashPassword
import br.pucpr.HighFlyAPI.users.responses.LoginResponse
import br.pucpr.HighFlyAPI.users.responses.UserResponse
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val jwt: Jwt
) {
    companion object{
        val log = LoggerFactory.getLogger(UserService::class.java)
        private val crypt = PasswordUtils()
    }

    fun save(user: User): User  {
        user.password = hashPassword(user.password)

        return userRepository.save(user)
    }

    fun findAll(dir: SortDir, role: String?) =
        role?.let { r ->
            when (dir) {
                SortDir.ASC -> userRepository.findByRole(r.uppercase()).sortedBy { it.name }
                SortDir.DESC -> userRepository.findByRole(r.uppercase()).sortedByDescending { it.name }
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

    fun login(email: String, password: String): LoginResponse? {
        val user = userRepository.findByEmail(email)

        if ( user == null ) {
            log.warn("User not found with email $email")
            return null
        }

        if (crypt.verifyPassword(password ,user.password)) {
            log.warn("Invalid Password!!!")
            return null
        }

        log.info("User logged in!")

        return LoginResponse(
            access_token = jwt.createToken(user),
            UserResponse(user)
        )
    }



}