package br.com.picpaytest.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
data class Wallet(
    @Id
    @Column(length = 36)
    val id: String = UUID.randomUUID().toString(),
    @Column(nullable = false)
    val balance: BigDecimal = BigDecimal.ZERO,
    @OneToOne
    @JoinColumn(name = "user_id")
    val applicationUser: ApplicationUser
)