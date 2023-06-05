package br.com.picpaytest.controller

import br.com.picpaytest.entity.ApplicationUser
import br.com.picpaytest.fixtures.EntityFixtureFactory
import br.com.picpaytest.repository.UserRepository
import br.com.picpaytest.service.UserService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class AuthControllerTest {
    @Autowired
    lateinit var controller: AuthController

    @Autowired
    lateinit var userRepository: UserRepository

    @MockBean
    lateinit var userService: UserService

    @Test
    fun getMeShouldReturnAuthenticatedUserDetails() {
        // given
        val user = userRepository.saveAndFlush(
            ApplicationUser(
                id = "d2012c14-106c-42dd-8f96-503b054219c5",
                email = "user@gmail.com",
                documentId = "123456",
                password = "123",
                fullName = "teste user",
                createdDate = LocalDateTime.now()
            )
        )
        given(userService.getMe()).willReturn(EntityFixtureFactory.User.applicationUserDetails(user))

        // when
        controller.getMe()

        // then
        then(userService).should(times(1)).getMe()
    }
}