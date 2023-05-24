package br.com.picpaytest.entity

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "`user`")
data class User(
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
    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: MutableSet<Role> = mutableSetOf(),
    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    val wallet: Wallet,
    @Column(nullable = false)
    val createdDate: LocalDateTime
)