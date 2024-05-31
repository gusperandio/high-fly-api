package br.pucpr.HighFlyAPI.orders

import br.pucpr.HighFlyAPI.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface OrderRepository : JpaRepository<Order, Long>