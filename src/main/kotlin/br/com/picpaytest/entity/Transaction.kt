package br.com.picpaytest.entity

import br.com.picpaytest.enum.TransactionStatus
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
data class Transaction(
    @Id
    @Column(length = 36)
    val id: String = UUID.randomUUID().toString(),
    @Column(nullable = false)
    val amount: BigDecimal,
    @Enumerated(value = EnumType.STRING)
    var status: TransactionStatus,
    @ManyToOne
    @JoinColumn(name = "sender_wallet_id")
    val senderWallet: Wallet,
    @ManyToOne
    @JoinColumn(name = "receiver_wallet_id")
    val receiverWallet: Wallet,
    @Column(nullable = false)
    val createdDate: LocalDateTime
)