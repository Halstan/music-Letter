package com.musicletter.app.controller

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("/token")
class TokenController {

    @Resource(name = "tokenServices")
    private lateinit var tokenService: ConsumerTokenServices

    @GetMapping(value = ["/anular/{token:.*}"])
    private fun revokeToken(@PathVariable token: String) {
        tokenService.revokeToken(token)
    }

}