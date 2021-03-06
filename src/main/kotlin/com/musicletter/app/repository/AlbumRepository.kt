package com.musicletter.app.repository

import com.musicletter.app.entity.Album
import org.springframework.data.jpa.repository.JpaRepository

interface AlbumRepository : JpaRepository<Album, Int> {

    fun getAlbumsByAutorIdAutor(idAutor: Int): List<Album>

}