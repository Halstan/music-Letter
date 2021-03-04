package com.musicletter.app.service

import com.musicletter.app.entity.Album
import com.musicletter.app.repository.AlbumRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AlbumService (
    val albumRepository: AlbumRepository
    ){

    @Transactional(readOnly = true)
    fun buscarTodos(): List<Album> =
        this.albumRepository.findAll()

    //@PreAuthorize("isAuthenticated()")
    fun manipularAlbum(album: Album): Album =
        this.albumRepository.save(album)

    @Transactional(readOnly = true)
    fun buscarPorId(idAlbum: Int): Optional<Album> =
        this.albumRepository.findById(idAlbum)

}