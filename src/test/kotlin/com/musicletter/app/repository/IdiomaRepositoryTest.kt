package com.musicletter.app.repository

import com.musicletter.app.entity.Idioma
import com.musicletter.app.repository.IdiomaRepository
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class IdiomaRepositoryTest(
    @Autowired
    val underTest: IdiomaRepository
    ){

    @AfterEach
   internal fun tearDown() {
        underTest.deleteAll()
    }

    @Test
    fun shouldFindByNombreExists() {
        // GIVEN
        val nombre = "Quechua"
        val idioma = Idioma()
        idioma.nombre = nombre

        underTest.save(idioma)
        // WHEN
        val expected = underTest.getIdiomaByNombre(nombre).isPresent

        //THEN
        assertThat(expected).isTrue
    }

    @Test
    fun shouldFindByNombreNotExistence() {
        // GIVEN
        val nombre = "adasd"
        /*val idioma = Idioma()
        idioma.nombre = nombre

        underTest.save(idioma)*/
        // WHEN
        val expected = underTest.getIdiomaByNombre(nombre).isPresent

        //THEN
        assertThat(expected).isFalse
    }

}