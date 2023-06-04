package br.com.picpaytest.controller

import br.com.picpaytest.dto.CredentialsDTO
import br.com.picpaytest.dto.MeDTO
import br.com.picpaytest.security.config.JWTUtil
import br.com.picpaytest.service.ApplicationUserDetails
import br.com.picpaytest.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
        @Autowired
        private val authenticationManager: AuthenticationManager,
        @Autowired
        private val jwtUtil: JWTUtil,
        @Autowired private val userService: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody credentials: CredentialsDTO): String? {
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                credentials.email, credentials.password
        )
        val authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        val user = authenticate.principal as ApplicationUserDetails
        return jwtUtil.generateToken(user)
    }

    @GetMapping("/me")
    fun getMe(): MeDTO = MeDTO.fromUser(userService.getMe())
}