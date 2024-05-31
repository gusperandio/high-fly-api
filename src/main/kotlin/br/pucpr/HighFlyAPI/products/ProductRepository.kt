package br.pucpr.HighFlyAPI.products

import br.pucpr.HighFlyAPI.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ProductRepository : JpaRepository<Product, Long>