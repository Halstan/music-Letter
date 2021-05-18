package com.musicletter.app.repository

import com.musicletter.app.entity.Album
import com.musicletter.app.entity.Autor
import com.musicletter.app.entity.Genero
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class AlbumRepositoryTest(
    @Autowired
    val underTest: AlbumRepository,
    @Autowired
    val autorRepository: AutorRepository,
    @Autowired
    val generoRepository: GeneroRepository
    ) {

    @AfterEach
    internal fun tearDown() {
        underTest.deleteAll()
    }

    @BeforeEach
    internal fun setUp() {
        val genero = Genero()
        genero.nombre = "Blues"

        val autor = Autor()
        autor.nombres = "Jose"
        autor.alias = "Pepe"

        val autorTran = autorRepository.save(autor)
        val generoTran = generoRepository.save(genero)

        val album = Album()
        album.nombre = "Carmesí"
        album.genero = generoTran
        album.autor = autorTran
        album.subGeneros = setOf(genero)

        underTest.save(album)
    }

    @Test
    fun shouldFindByIdAutor() {

        val expected = underTest.getAlbumsByAutorIdAutor(1)

        val map = Tuple("Carmesí")
        print(expected)
        assertThat(expected).isNotEmpty

    }

    @Test
    fun shouldContainCarmesi() {
        val expected = underTest.getAlbumsByAutorIdAutor(1)

        //val map = Tuple("Carmesí")

        assertThat(expected).isNotEmpty.extracting(Album::nombre.name).contains("Carmesí")
    }

    @Test
    fun shouldFindByIdAutorNotExists() {
        val expected = underTest.getAlbumsByAutorIdAutor(77)
        assertThat(expected).isEmpty()
    }
}