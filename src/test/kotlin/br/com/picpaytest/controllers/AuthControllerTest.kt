package br.com.picpaytest.controllers

import br.com.picpaytest.dto.LoginDto
import br.com.picpaytest.entity.User
import br.com.picpaytest.repository.UserRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class AuthControllerTest {
    @Autowired
    lateinit var controller: AuthController

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun userShouldNotAuthenticateWithWrongProvider() {
        val provider = "invalid_provider"
        val login = LoginDto(
            email = "invalid_provider@email.com",
            password = "123"
        )
        val result = controller.authenticate(provider, login)
        assertThat(result, `is`(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid provider")))
    }

    @Test
    fun userShouldAuthenticateWithValidProvider() {
        val provider = "user"
        val login = LoginDto(
            email = "user@gmail.com",
            password = "123"
        )

        userRepository.saveAndFlush(
            User(
                id = "d2012c14-106c-42dd-8f96-503b054219c5",
                email = "user@gmail.com",
                documentId = "123456",
                password = "123"
            )
        )
        val result = controller.authenticate(provider, login)
        assertThat(result, `is`(ResponseEntity.status(HttpStatus.OK).body("Authenticated successfully with provider $provider")))
    }
}