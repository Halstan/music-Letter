package com.musicletter.app.auditor

import org.springframework.data.domain.AuditorAware
import java.util.*

class AuditorAwareImpl : AuditorAware<String>{

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.empty()
    }
}