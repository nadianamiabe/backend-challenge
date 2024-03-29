package br.com.picpaytest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class PicpayTestApplication

fun main(args: Array<String>) {
	runApplication<PicpayTestApplication>(*args)
}
