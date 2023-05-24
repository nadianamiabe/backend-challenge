package br.com.picpaytest.repository

import br.com.picpaytest.entity.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository : JpaRepository<Wallet, String> {
}