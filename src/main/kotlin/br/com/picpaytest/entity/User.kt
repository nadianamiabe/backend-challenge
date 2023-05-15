package br.com.picpaytest.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "`user`")
class User(
    @Id
    override val id: String = UUID.randomUUID().toString(),
    @Column(nullable = false, unique = true)
    override var email: String,
    @Column(nullable = false, unique = true)
    override var documentId: String,
    @Column(nullable = false)
    override var password: String
) : Provider