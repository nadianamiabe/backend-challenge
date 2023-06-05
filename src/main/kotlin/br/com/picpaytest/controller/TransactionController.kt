package br.com.picpaytest.controller

import br.com.picpaytest.dto.TransactionInputDTO
import br.com.picpaytest.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api-transactions")
class TransactionController(
    @Autowired
    private val transactionService: TransactionService
) {
    @PostMapping("/transactions")
    fun createTransaction(@RequestBody transactionInputDTO: TransactionInputDTO): ResponseEntity<String> {
        transactionService.create(transactionInputDTO)
        return ResponseEntity.ok("ok")
    }
}