package br.com.picpaytest.client

import br.com.picpaytest.dto.ExternalAuthorizationDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(url = "https://run.mocky.io/v3/", name = "auth-api")
interface ExternalAuthorizationMockApi {
    @GetMapping("/8fafdd68-a090-496f-8c9a-3442cf30dae6")
    fun authorize(): ExternalAuthorizationDTO
}