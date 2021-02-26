package com.musicletter.app.service

import com.musicletter.app.entity.Autor
import com.musicletter.app.repository.AutorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AutorService (
    val autorRepository: AutorRepository
    ){

    @Transactional(readOnly = true)
    fun findAll(): List<Autor> =
        this.autorRepository.findAll()

    fun manipularAutor(autor: Autor): Autor =
        this.autorRepository.save(autor)

    @Transactional(readOnly = true)
    fun buscarPorId(idAutor: Int): Optional<Autor> =
        this.autorRepository.findById(idAutor)
}