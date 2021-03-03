package com.musicletter.app.service

import com.musicletter.app.entity.ConfirmationToken
import com.musicletter.app.repository.ConfirmationTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ConfirmationTokenService (
    val confirmationTokenRepository: ConfirmationTokenRepository
    ) {

    @Transactional(readOnly = true)
    fun findByConfirmationToken(confirmationToken: String): Optional<ConfirmationToken> {
        return confirmationTokenRepository.findByConfirmationTokenAndEstadoIsTrue(confirmationToken)
    }

    @Transactional
    fun save(confirmationToken: ConfirmationToken?): ConfirmationToken? {
        return confirmationTokenRepository.save(confirmationToken!!)
    }

    @Transactional
    fun desactivarToken(token: String): ConfirmationToken {
        val op: Optional<ConfirmationToken> =
            confirmationTokenRepository.findByConfirmationTokenAndEstadoIsTrue(token)
        confirmationTokenRepository.desactivarToken(token)
        return op.get()
    }

}