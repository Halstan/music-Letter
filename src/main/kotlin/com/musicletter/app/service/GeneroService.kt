package com.musicletter.app.service

import com.musicletter.app.entity.Genero
import com.musicletter.app.repository.GeneroRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GeneroService (
    val generoRepository: GeneroRepository
    ){

    @Transactional(readOnly = true)
    fun buscarTodos(): List<Genero> =
        this.generoRepository.findAll()

    fun manipularGenero(genero: Genero) =
        this.generoRepository.save(genero)

    @Transactional(readOnly = true)
    fun buscarPorId(idGenero: Int) =
        this.generoRepository.findById(idGenero)

}