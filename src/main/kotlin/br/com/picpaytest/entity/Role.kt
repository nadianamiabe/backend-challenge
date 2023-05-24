package br.com.picpaytest.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "role")
data class Role(
    @Id
    @Column(nullable = false, unique = true)
    val name: String,
    @ManyToMany(mappedBy = "roles")
    val users: MutableSet<User> = mutableSetOf()
)