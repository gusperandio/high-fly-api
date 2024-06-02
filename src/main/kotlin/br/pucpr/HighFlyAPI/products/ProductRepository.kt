package br.pucpr.HighFlyAPI.products

import br.pucpr.HighFlyAPI.users.User
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component

interface ProductRepository : JpaRepository<Product, Long>{
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.amount = p.amount + :quantityToAddOrRemove WHERE p IN :products")
    fun restoreAmounts(@Param("products") products: Product, @Param("quantityToAdd") quantityToAdd: Int): Int

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.amount = p.amount - :quantityToRemove WHERE p.name = :name")
    fun removeAmounts(@Param("name") name: String, @Param("quantityToRemove") quantityToRemove: Int): Int

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    fun findByName(@Param("name") name: String): Product?
}