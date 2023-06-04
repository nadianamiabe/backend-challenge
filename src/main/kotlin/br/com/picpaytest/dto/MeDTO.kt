package br.com.picpaytest.dto

import br.com.picpaytest.entity.Role
import br.com.picpaytest.service.ApplicationUserDetails

data class MeDTO(
        val username: String,
        val authorities: List<Role>,
        val isEnabled: Boolean = true,
        val isCredentialsNonExpired: Boolean = true
) {
    companion object {
        fun fromUser(userDetails: ApplicationUserDetails) = MeDTO(
                username = userDetails.username,
                authorities = userDetails.authorities,
                isEnabled = userDetails.isEnabled,
                isCredentialsNonExpired = userDetails.isCredentialsNonExpired
        )
    }
}