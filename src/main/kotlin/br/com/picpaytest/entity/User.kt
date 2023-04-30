package br.com.picpaytest.entity

import java.util.UUID

class User(
//    @Id
    id: String = UUID.randomUUID().toString(),
//    @Column(nullable = false, unique = true)
    email: String,
//    @Column(nullable = false, unique = true)
    documentId: String,
//    @Column(nullable = false)
    password: String
): Provider(
    id = id,
    email = email,
    documentId = documentId,
    password = password
)