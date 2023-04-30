package br.com.picpaytest.entity

import java.util.*

abstract class Provider (
    private val id: String = UUID.randomUUID().toString(),
    private val email: String,
    private val documentId: String,
    private val password: String
)