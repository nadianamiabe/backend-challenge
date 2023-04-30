package br.com.picpaytest.controllers

import br.com.picpaytest.entity.Provider
import br.com.picpaytest.entity.Shopkeeper
import br.com.picpaytest.entity.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController {
    @PostMapping(path = ["/{provider}"])
    fun authenticate(@PathVariable("provider") provider: String): ResponseEntity<String> {
        val allowedProviders = listOf("user", "shopkeeper")
        if (!allowedProviders.contains(provider)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid provider")
        }
        val persistedProvider = getProvider(provider)
        println(persistedProvider)
        return ResponseEntity.status(HttpStatus.OK).body("Authenticated successfully with provider $provider")
    }

    fun getProvider(provider: String): Provider {
        return when(provider) {
            "user" -> User(email = "user@user.com", documentId = "123", password = "123")
            "shopkeeper" -> Shopkeeper(email = "shopkeeper@shopkeeper.com", documentId = "321", password = "123")
            else -> throw Exception("Provider not found")
        }
    }
}