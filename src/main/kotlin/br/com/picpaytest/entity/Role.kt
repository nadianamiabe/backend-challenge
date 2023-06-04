package br.com.picpaytest.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Role(
    @Id
    @Column(nullable = false)
    val name: String
) : GrantedAuthority {
    override fun getAuthority() = name
}