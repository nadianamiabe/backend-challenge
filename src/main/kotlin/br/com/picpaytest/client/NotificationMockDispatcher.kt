package br.com.picpaytest.client

import br.com.picpaytest.dto.SentNotificationDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(url = "https://run.mocky.io/v3/", name = "notification-dispatcher")
interface NotificationMockDispatcher {
    @GetMapping("/b19f7b9f-9cbf-4fc6-ad22-dc30601aec04")
    fun send(): SentNotificationDTO
}