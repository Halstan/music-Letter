package com.musicletter.app.service

import com.musicletter.app.entity.Permiso
import com.musicletter.app.repository.PermisoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PermisoService(
        val permisoRepository: PermisoRepository
    ) {

    @Transactional(readOnly = true)
    fun findAll(): List<Permiso> =
        this.permisoRepository.findAll()

    fun findByNombre(nombre: String): Optional<Permiso> =
        this.permisoRepository.getPermisoByNombre(nombre)
}
