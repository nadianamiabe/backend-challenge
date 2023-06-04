package br.com.picpaytest.repository

import br.com.picpaytest.entity.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<ApplicationUser, String> {
    fun findByEmail(email: String): ApplicationUser?
}