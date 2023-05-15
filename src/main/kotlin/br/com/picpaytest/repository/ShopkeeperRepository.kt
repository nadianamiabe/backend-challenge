package br.com.picpaytest.repository

import br.com.picpaytest.entity.Shopkeeper
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopkeeperRepository : JpaRepository<Shopkeeper, String> {
    fun findByEmail(email: String): Shopkeeper
}