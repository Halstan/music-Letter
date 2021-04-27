package com.musicletter.app.service

import com.musicletter.app.entity.Cancion
import com.musicletter.app.repository.CancionRepository
import com.musicletter.app.repository.UsuarioRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CancionService (
    val cancionRepository: CancionRepository,
    val usuarioRepository: UsuarioRepository
    ){

    @Transactional(readOnly = true)
    fun buscarTodos(): List<Cancion> =
        this.cancionRepository.findAll()

    @PreAuthorize("isAuthenticated()")
    fun manipularCancion(cancion: Cancion): Cancion {
        val username = SecurityContextHolder.getContext().authentication.name
        val usuario = this.usuarioRepository.findByNombreDeUsuarioIgnoreCase(username)
        return if (usuario.isPresent) {
            cancion.usuario = usuario.get()
            this.cancionRepository.save(cancion)
        } else {
            Cancion()
        }
    }

    @PreAuthorize("isAuthenticated()")
    //@PreAuthorize("isAuthenticated() && hasPermission(#idCancion, 'ROLE_ADMIN')")
    @Transactional(readOnly = true)
    fun buscarPorId(idCancion: String): Optional<Cancion> =
        this.cancionRepository.findById(idCancion)

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    fun buscarCancionesPorUsuario(): List<Cancion> {
        val username = SecurityContextHolder.getContext().authentication.name
        return this.cancionRepository.getCancionsByUsuarioNombreDeUsuario(username)
    }

    @Transactional(readOnly = true)
    fun buscarCancionesPorAlbum(idAlbum: Int) =
        this.cancionRepository.getCancionsByAlbumIdAlbum(idAlbum)

    @Transactional(readOnly = true)
    fun buscarCancionPorNombre(nombre: String) =
        this.cancionRepository.getCancionsByNombreStartsWith(nombre)

}