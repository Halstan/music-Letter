package com.musicletter.app.service

import com.musicletter.app.entity.Cancion
import com.musicletter.app.repository.CancionRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CancionService (
    val cancionRepository: CancionRepository
    ){

    @Transactional(readOnly = true)
    fun buscarTodos(): List<Cancion> =
        this.cancionRepository.findAll()

    //@PreAuthorize("isAuthenticated()")
    fun manipularCancion(cancion: Cancion): Cancion =
        this.cancionRepository.save(cancion)

    @Transactional(readOnly = true)
    fun buscarPorId(idCancion: String): Optional<Cancion> =
        this.cancionRepository.findById(idCancion)

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    fun buscarCancionesPorUsuario(): List<Cancion> {
        val username = SecurityContextHolder.getContext().authentication.principal as String
        return this.cancionRepository.getCancionsByUsuarioNombreDeUsuario(username)
    }

    @Transactional(readOnly = true)
    fun buscarCancionesPorAlbum(idAlbum: Int) =
        this.cancionRepository.getCancionsByAlbumIdAlbum(idAlbum)

}