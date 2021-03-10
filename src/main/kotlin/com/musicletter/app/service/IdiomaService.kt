package com.musicletter.app.service

import com.musicletter.app.entity.Album
import com.musicletter.app.entity.Idioma
import com.musicletter.app.repository.IdiomaRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class IdiomaService constructor (
    val idiomaRepository: IdiomaRepository
    ){

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    fun buscarTodos(): List<Idioma> =
        this.idiomaRepository.findAll()

    fun manipularIdioma(idioma: Idioma): Idioma =
        this.idiomaRepository.save(idioma)

    @Transactional(readOnly = true)
    fun buscarIdioma(idIdioma: Int): Optional<Idioma> =
        this.idiomaRepository.findById(idIdioma)

}