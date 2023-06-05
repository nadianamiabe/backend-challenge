package br.com.picpaytest.fixtures

import br.com.picpaytest.entity.ApplicationUser
import br.com.picpaytest.service.ApplicationUserDetails

internal object EntityFixtureFactory {
    object User {
        fun applicationUserDetails(user: ApplicationUser) = ApplicationUserDetails(user)
    }
}