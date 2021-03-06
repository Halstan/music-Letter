package com.musicletter.app.repository

import com.musicletter.app.entity.Cancion
import org.springframework.data.jpa.repository.JpaRepository

interface CancionRepository : JpaRepository<Cancion, String>{

    fun getCancionsByUsuarioNombreDeUsuario(nombreDeUsuario: String): List<Cancion>

    fun getCancionsByAlbumIdAlbum(idAlbum: Int): List<Cancion>

    fun getCancionsByNombreStartsWith(nombre: String): List<Cancion>

}