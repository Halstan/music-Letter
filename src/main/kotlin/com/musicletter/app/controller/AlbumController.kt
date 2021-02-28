package com.musicletter.app.controller

import com.musicletter.app.entity.Album
import com.musicletter.app.entity.Cancion
import com.musicletter.app.mapper.AlbumMapperImpl
import com.musicletter.app.service.AlbumService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/albumes")
class AlbumController (
    val albumService: AlbumService,
    val albumMapper: AlbumMapperImpl
    ){

    companion object {
        const val type = "application/json;charset=UTF-8"
    }

    @GetMapping(produces = [type])
    private fun findAll(): ResponseEntity<*> {
        val albumes = this.albumService.buscarTodos()
        return ResponseEntity(albumMapper.toAlbumDTOs(albumes), HttpStatus.OK)
    }

    @PostMapping(produces = [CancionController.type])
    private fun manipularAlbum(@RequestBody album: Album): ResponseEntity<*> {
        return ResponseEntity(albumMapper.toAlbumDTO(this.albumService.manipularAlbum(album)), HttpStatus.CREATED)
    }

    @GetMapping(value = ["{idAlbum}"])
    private fun buscarPorId(@PathVariable idAlbum: Int): ResponseEntity<*> {
        val album: Optional<Album> = this.albumService.buscarPorId(idAlbum);
        return if (album.isPresent) ResponseEntity(albumMapper.toAlbumDTO(album.get()), HttpStatus.OK)
        else ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
    }

}