package br.com.picpaytest.service

import br.com.picpaytest.dto.TransactionInputDTO
import br.com.picpaytest.entity.ApplicationUser
import br.com.picpaytest.entity.Transaction
import br.com.picpaytest.enum.TransactionStatus
import br.com.picpaytest.repository.TransactionRepository
import br.com.picpaytest.repository.UserRepository
import br.com.picpaytest.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class TransactionService(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val transactionRepository: TransactionRepository,
    @Autowired
    private val walletRepository: WalletRepository
) {
    @Transactional
    fun create(transactionInputDTO: TransactionInputDTO): Transaction {
        val payer = userRepository.findByIdOrNull(transactionInputDTO.userId)
                ?: throw NotFoundException()

        userRepository.findByIdOrNull(transactionInputDTO.payeeId)
                ?: throw NotFoundException()

        if (canUserTransfer(payer)) throw Exception("Shopkeeper user can not transfer, only receive transaction")

        if (!checkUserBalance(payer, transactionInputDTO.amount)) throw Exception("Insufficient balance")

        val senderWallet = walletRepository.getByApplicationUserId(payer.id)
        val receiverWallet = walletRepository.getByApplicationUserId(transactionInputDTO.payeeId)

        val transaction = transactionRepository.save(
                Transaction(
                        amount = transactionInputDTO.amount,
                        status = TransactionStatus.PENDING,
                        senderWallet = senderWallet,
                        receiverWallet = receiverWallet,
                        createdDate = LocalDateTime.now()
                )
        )

        senderWallet.withdraw(transactionInputDTO.amount)
        receiverWallet.deposit(transactionInputDTO.amount)

        return transaction
    }

    private fun canUserTransfer(user: ApplicationUser): Boolean {
        return user.roles[0].name == "SHOPKEEPER_USER"
    }

    private fun checkUserBalance(user: ApplicationUser, amount: BigDecimal): Boolean {
        val wallet = walletRepository.getByApplicationUserId(user.id)
        return wallet.balance >= amount
    }
}