package br.com.picpaytest.service

import br.com.picpaytest.entity.ApplicationUser
import br.com.picpaytest.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
        @Autowired private val userRepository: UserRepository
): UserDetailsService {
    @PreAuthorize("isAuthenticated()")
    fun getMe(): ApplicationUserDetails {
        val userDetails = SecurityContextHolder
                .getContext()
                .authentication
                .principal as ApplicationUser
        return ApplicationUserDetails(
                userDetails
        )
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw RuntimeException()
        return ApplicationUserDetails(user)
    }
}