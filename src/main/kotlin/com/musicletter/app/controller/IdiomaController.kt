package com.musicletter.app.controller

import com.musicletter.app.entity.Idioma
import com.musicletter.app.mapper.IdiomaMapperImpl
import com.musicletter.app.service.IdiomaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/idiomas")
class IdiomaController constructor (
    val idiomaService: IdiomaService
    ){

    @Autowired
    lateinit var idiomaMapper: IdiomaMapperImpl

    companion object {
        const val type = "application/json;charset=UTF-8"
    }

    @GetMapping(produces = [type])
    private fun findAll(): ResponseEntity<*> {
        return ResponseEntity(idiomaMapper.toIdiomaDTOs(this.idiomaService.buscarTodos()), HttpStatus.OK);
    }

    @PostMapping(produces = [type])
    private fun manipularIdioma(@RequestBody idioma: Idioma): ResponseEntity<*> {
        return ResponseEntity(idiomaMapper.toIdiomaDTO(this.idiomaService.manipularIdioma(idioma)), HttpStatus.CREATED)
    }

    @GetMapping(value = ["{idIdioma}"])
    private fun buscarPorId(@PathVariable idIdioma: Int): ResponseEntity<*> {
        val idioma: Optional<Idioma> = this.idiomaService.buscarIdioma(idIdioma);
        return if (idioma.isPresent) ResponseEntity(idiomaMapper.toIdiomaDTO(idioma.get()), HttpStatus.OK)
                else ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
    }

}