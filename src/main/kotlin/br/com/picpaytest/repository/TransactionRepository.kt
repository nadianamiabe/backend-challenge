package br.com.picpaytest.repository

import br.com.picpaytest.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, String> {
}