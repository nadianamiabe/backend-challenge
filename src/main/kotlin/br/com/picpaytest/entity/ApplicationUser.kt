package br.com.picpaytest.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
data class ApplicationUser(
    @Id
    @Column(length = 36)
    val id: String = UUID.randomUUID().toString(),
    @Column(nullable = false, unique = true)
    val email: String,
    @Column(nullable = false, unique = true)
    val documentId: String,
    @Column(nullable = false)
    val fullName: String,
    @Column(nullable = false)
    val password: String,
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_roles")
    val roles: List<Role> = mutableListOf(),
    @Column(nullable = false)
    val createdDate: LocalDateTime
)