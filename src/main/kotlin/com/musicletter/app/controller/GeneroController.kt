package com.musicletter.app.controller

import com.musicletter.app.entity.Genero
import com.musicletter.app.mapper.GeneroMapperImpl
import com.musicletter.app.service.GeneroService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/generos")
class GeneroController (
    val generoService: GeneroService,
    val generoMapper: GeneroMapperImpl
    ){

    companion object {
        const val type = "application/json;charset=UTF-8"
    }
    
    @GetMapping(produces = [type])
    private fun findAll(): ResponseEntity<*> {
        return ResponseEntity(generoMapper.toGeneroDTOs(this.generoService.buscarTodos()), HttpStatus.OK)
    }
    
    @PostMapping(produces = [type])
    private fun manipularGenero(@RequestBody genero: Genero): ResponseEntity<*> {
        val genero1: Genero = this.generoService.manipularGenero(genero);
        return ResponseEntity(generoMapper.toGeneroDTO(genero1), HttpStatus.OK)
    }

    @GetMapping(value = ["{idGenero}"])
    private fun buscarPorId(@PathVariable idGenero: Int): ResponseEntity<*> {
        val genero = this.generoService.buscarPorId(idGenero);
        return if (genero.isPresent) ResponseEntity(generoMapper.toGeneroDTO(genero.get()), HttpStatus.OK)
                else ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
    }
    
}