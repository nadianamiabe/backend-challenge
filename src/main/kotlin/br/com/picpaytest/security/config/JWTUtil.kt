package br.com.picpaytest.security.config

import br.com.picpaytest.service.ApplicationUserDetails
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Component
class JWTUtil {
    @Value("\${jwt.secret}")
    private lateinit var secret : String
    fun generateToken(user: ApplicationUserDetails): String? {
        return JWT.create()
                .withSubject(user.username)
                .withClaim("username", user.username)
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-03:00"))
                )
                .sign(Algorithm.HMAC256(secret))
    }

    fun getSubject(token: String): String {
        return JWT.require(Algorithm.HMAC256(secret))
                .build().verify(token).subject
    }
}