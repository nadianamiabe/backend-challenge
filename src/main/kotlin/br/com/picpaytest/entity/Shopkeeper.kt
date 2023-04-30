package br.com.picpaytest.entity

import java.util.*

class Shopkeeper(
    id: String = UUID.randomUUID().toString(),
    email: String,
    documentId: String,
    password: String
): Provider(
    id = id,
    email = email,
    documentId = documentId,
    password = password
)