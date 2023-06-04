package br.com.picpaytest.service

import br.com.picpaytest.entity.ApplicationUser
import org.springframework.security.core.userdetails.UserDetails

class ApplicationUserDetails(private val applicationUser: ApplicationUser) : UserDetails {
    override fun getAuthorities() = applicationUser.roles

    override fun getPassword() = applicationUser.password

    override fun getUsername() = applicationUser.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}