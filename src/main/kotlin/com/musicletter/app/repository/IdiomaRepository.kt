package com.musicletter.app.repository

import com.musicletter.app.entity.Idioma
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IdiomaRepository : JpaRepository<Idioma, Int>{

    fun getIdiomaByNombre(nombre: String): Optional<Idioma>

}