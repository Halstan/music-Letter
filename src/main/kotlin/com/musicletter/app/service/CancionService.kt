package com.musicletter.app.service

import com.musicletter.app.entity.Cancion
import com.musicletter.app.repository.CancionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CancionService (
    val cancionRepository: CancionRepository
    ){

    @Transactional(readOnly = true)
    fun buscarTodos(): List<Cancion> =
        this.cancionRepository.findAll()

    fun manipularCancion(cancion: Cancion): Cancion =
        this.cancionRepository.save(cancion)

    @Transactional(readOnly = true)
    fun buscarPorId(idCancion: Int): Optional<Cancion> =
        this.cancionRepository.findById(idCancion)
}