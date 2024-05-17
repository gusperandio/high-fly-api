package br.pucpr.HighFlyAPI.users

import org.springframework.stereotype.Component

@Component
class UserRepository {
    private var lastId = 0L;
    private val users =
        mutableMapOf<Long, User>()

    fun save(user: User) : User{
        val id = user.id

        if(id == null){
            lastId += 1
            user.id = lastId
            users[lastId] = user
        }else{
            users[id] = user
        }
        return user
    }

    fun findAll(): List<User>{
        return users.values.toList()
    }

    fun findByIdOrNull(id: Long) = users[id]
}