package com.musicletter.app.controller

import com.musicletter.app.entity.Cancion
import com.musicletter.app.entity.Idioma
import com.musicletter.app.mapper.CancionMapperImpl
import com.musicletter.app.service.CancionService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/canciones")
class CancionController (
    val cancionService: CancionService,
    val cancionMapper: CancionMapperImpl
    ){

    companion object {
        const val type = "application/json;charset=UTF-8"
    }

    @GetMapping(produces = [type])
    private fun findAll(): ResponseEntity<*> {
        val canciones = this.cancionService.buscarTodos()
        return ResponseEntity(cancionMapper.toCancionDTOs(canciones), HttpStatus.OK)
    }

    @PostMapping(produces = [type])
    private fun manipularIdioma(@RequestBody cancion: Cancion): ResponseEntity<*> {
        return ResponseEntity(cancionMapper.toCancionDTO(this.cancionService.manipularCancion(cancion)), HttpStatus.CREATED)
    }

    @GetMapping(value = ["{idCancion}"])
    private fun buscarPorId(@PathVariable idCancion: Int): ResponseEntity<*> {
        val cancion: Optional<Cancion> = this.cancionService.buscarPorId(idCancion);
        return if (cancion.isPresent) ResponseEntity(cancionMapper.toCancionDTO(cancion.get()), HttpStatus.OK)
        else ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
    }

}