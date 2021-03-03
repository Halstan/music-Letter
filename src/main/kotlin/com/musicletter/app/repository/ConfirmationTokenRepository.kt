package com.musicletter.app.repository

import com.musicletter.app.entity.ConfirmationToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ConfirmationTokenRepository : JpaRepository<ConfirmationToken, Long> {

    fun findByConfirmationTokenAndEstadoIsTrue(confirmationToken: String): Optional<ConfirmationToken>

    @Modifying
    @Query("UPDATE ConfirmationToken ct set ct.estado = false where ct.confirmationToken = ?1")
    fun desactivarToken(username: String?): Int?

}