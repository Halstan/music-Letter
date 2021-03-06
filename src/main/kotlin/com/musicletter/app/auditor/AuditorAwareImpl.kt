package com.musicletter.app.auditor

import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

class AuditorAwareImpl : AuditorAware<String>{

    override fun getCurrentAuditor(): Optional<String> {
        val authentication = SecurityContextHolder.getContext().authentication

        return if (authentication == null || !authentication.isAuthenticated) {
            Optional.empty()
        } else Optional.of(authentication.name)

    }
}