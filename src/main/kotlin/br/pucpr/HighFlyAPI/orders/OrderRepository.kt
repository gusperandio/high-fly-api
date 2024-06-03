package br.pucpr.HighFlyAPI.orders

import br.pucpr.HighFlyAPI.users.User
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component

interface OrderRepository : JpaRepository<Order, Long>{

    @Modifying
    @Transactional
    @Query("UPDATE Order d SET d.status = :status WHERE d.id = :id")
    fun updateOrderStatus(@Param("id") id: Long, @Param("status") status: Boolean): Int


    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Order o " +
            "JOIN o.userOrder r " +
            " WHERE r.id = :userId " +
            "AND o.status = false")
    fun existsOrderToFinish(@Param("userId") userId: Long): Boolean

    @Query("SELECT u FROM Order u" +
            " WHERE u.identify = :code ")
    fun findByIdentify(@Param("code") code: String): Order?

}