package com.musicletter.app.controller

import com.musicletter.app.entity.Autor
import com.musicletter.app.mapper.AutorMapperImpl
import com.musicletter.app.service.AutorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/autores")
class AutorController (
    val autorService: AutorService,
    val autorMapper: AutorMapperImpl
    ){

    companion object {
        const val type = "application/json;charset=UTF-8"
    }

    @GetMapping(produces = [type])
    private fun buscarTodos(): ResponseEntity<*> {
        return ResponseEntity(autorMapper.toAutorDTOs(this.autorService.findAll()), HttpStatus.OK)
    }

    @PostMapping(produces = [type])
    private fun manipularAutor(@RequestBody autor: Autor): ResponseEntity<*> {
        val autor1 = this.autorService.manipularAutor(autor)
        return ResponseEntity(autorMapper.toAutorDTO(autor1), HttpStatus.CREATED)
    }

    @GetMapping(value = ["{idAutor}"])
    private fun buscarPorId(@PathVariable idAutor: Int): ResponseEntity<*> {
        val autor: Optional<Autor> = this.autorService.buscarPorId(idAutor)
        return if (autor.isPresent) ResponseEntity(autorMapper.toAutorDTO(autor.get()), HttpStatus.OK)
                else ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
    }

}