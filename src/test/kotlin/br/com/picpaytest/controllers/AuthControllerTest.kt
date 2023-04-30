package br.com.picpaytest.controllers

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    lateinit var controller: AuthController

    @Test
    fun userShouldNotAuthenticateWithWrongProvider() {
        val provider = "invalid_provider"
        val result = controller.authenticate(provider)
        assertThat(result, `is`(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid provider")))
    }

    @Test
    fun userShouldAuthenticateWithValidProvider() {
        val provider = "user"
        val result = controller.authenticate(provider)
        assertThat(result, `is`(ResponseEntity.status(HttpStatus.OK).body("Authenticated successfully with provider $provider")))
    }
}