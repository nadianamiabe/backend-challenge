package br.com.picpaytest.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "wallet")
data class Wallet(
    @Id
    @Column(length = 36)
    val id: String = UUID.randomUUID().toString(),
    @Column(nullable = false)
    val balance: BigDecimal = BigDecimal.ZERO,
    @OneToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @OneToMany(mappedBy = "senderWallet", cascade = [CascadeType.ALL])
    val outgoingTransactions: List<Transaction>,
    @OneToMany(mappedBy = "receiverWallet", cascade = [CascadeType.ALL])
    val incomingTransactions: List<Transaction>
)