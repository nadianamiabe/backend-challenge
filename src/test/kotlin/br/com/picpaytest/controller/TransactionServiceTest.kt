package br.com.picpaytest.controller

import br.com.picpaytest.dto.TransactionInputDTO
import br.com.picpaytest.entity.ApplicationUser
import br.com.picpaytest.entity.Role
import br.com.picpaytest.entity.Wallet
import br.com.picpaytest.enum.TransactionStatus
import br.com.picpaytest.repository.UserRepository
import br.com.picpaytest.repository.WalletRepository
import br.com.picpaytest.service.TransactionService
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TransactionServiceTest {
    @Autowired
    lateinit var transactionService: TransactionService

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var walletRepository: WalletRepository

    @Test
    fun shouldThrowNotFoundExceptionIfUserDoesntExist() {
        val input = TransactionInputDTO(
                userId = "111",
                payeeId = "123",
                amount = BigDecimal(100.00)
        )
        assertThrows<NotFoundException> {
            transactionService.create(input)
        }
    }

    @Test
    fun shouldThrowNotFoundExceptionIfPayeeDoesntExist() {
        val user = userRepository.saveAndFlush(ApplicationUser(
                email = "teste@teste.com",
                documentId = "111",
                fullName = "teste teste",
                password = "123",
                createdDate = LocalDateTime.now()
        ))

        val input = TransactionInputDTO(
                userId = user.id,
                payeeId = "123",
                amount = BigDecimal(100.00)
        )
        assertThrows<NotFoundException> {
            transactionService.create(input)
        }
    }

    @Test
    fun shouldThrowExceptionIfUserIsShopkeeper() {
        val user = userRepository.saveAndFlush(ApplicationUser(
                email = "shopkeeper@teste.com",
                documentId = "111",
                fullName = "teste teste",
                password = "123",
                createdDate = LocalDateTime.now(),
                roles = listOf(Role("SHOPKEEPER_USER"))
        ))

        val input = TransactionInputDTO(
                userId = user.id,
                payeeId = "123",
                amount = BigDecimal(100.00)
        )
        assertThrows<Exception> {
            transactionService.create(input)
        }
    }

    @Test
    fun shouldThrowExceptionIfInsufficientBalance() {
        val user = userRepository.saveAndFlush(ApplicationUser(
                email = "teste@teste.com",
                documentId = "111",
                fullName = "teste teste",
                password = "123",
                createdDate = LocalDateTime.now(),
                roles = listOf(Role("COMMON_USER"))
        ))

        walletRepository.saveAndFlush(Wallet(
                balance = BigDecimal(50.00),
                applicationUser = user
        ))

        val payee = userRepository.saveAndFlush(ApplicationUser(
                email = "teste22@teste.com",
                documentId = "222",
                fullName = "teste teste 2",
                password = "1234",
                createdDate = LocalDateTime.now(),
                roles = listOf(Role("COMMON_USER"))
        ))

        val input = TransactionInputDTO(
                userId = user.id,
                payeeId = payee.id,
                amount = BigDecimal(100.00)
        )
        assertThrows<Exception> {
            transactionService.create(input)
        }
    }

    @Test
    fun shouldCreatePendingTransaction() {
        val user = userRepository.saveAndFlush(ApplicationUser(
                email = "teste@teste.com",
                documentId = "111",
                fullName = "teste teste",
                password = "123",
                createdDate = LocalDateTime.now(),
                roles = listOf(Role("COMMON_USER"))
        ))

        val payee = userRepository.saveAndFlush(ApplicationUser(
                email = "teste22@teste.com",
                documentId = "222",
                fullName = "teste teste 2",
                password = "1234",
                createdDate = LocalDateTime.now(),
                roles = listOf(Role("COMMON_USER"))
        ))

        walletRepository.saveAndFlush(Wallet(
                balance = BigDecimal(100.00),
                applicationUser = user
        ))

        walletRepository.saveAndFlush(Wallet(
                balance = BigDecimal(100.00),
                applicationUser = payee
        ))

        val input = TransactionInputDTO(
                userId = user.id,
                payeeId = payee.id,
                amount = BigDecimal(50.00)
        )

        val transaction = transactionService.create(input)

        MatcherAssert.assertThat(transaction.status, CoreMatchers.`is`(TransactionStatus.PENDING))
        MatcherAssert.assertThat(transaction.receiverWallet.balance.toInt(), CoreMatchers.`is`(150))
        MatcherAssert.assertThat(transaction.senderWallet.balance.toInt(), CoreMatchers.`is`(50))
    }
}