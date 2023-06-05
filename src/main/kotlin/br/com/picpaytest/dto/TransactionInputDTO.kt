package br.com.picpaytest.dto

import java.math.BigDecimal

data class TransactionInputDTO(
    val userId: String,
    val payeeId: String,
    val amount: BigDecimal
)
