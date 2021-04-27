package com.musicletter.app.repository

import com.musicletter.app.entity.Permiso
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PermisoRepository: JpaRepository<Permiso, Int> {

    fun getPermisoByNombre(nombre: String): Optional<Permiso>

}