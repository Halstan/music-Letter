package com.musicletter.app.service

import com.musicletter.app.entity.Genero
import com.musicletter.app.repository.GeneroRepository
import org.assertj.core.api.AssertionsForClassTypes.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GeneroServiceTest {

    @Mock
    lateinit var generoRepository: GeneroRepository
    lateinit var underTest: GeneroService

    @BeforeEach
    internal fun setUp() {
        underTest = GeneroService(generoRepository)

        val genero = Genero()
        genero.nombre = "Rock"

        generoRepository.save(genero)
    }

    @Test
    fun canFindAll() {
        underTest.buscarTodos()

        verify(generoRepository).findAll()
    }

    @Test
    fun shouldSearchByIdNotExists() {
        val genero = underTest.buscarPorId(1)

        assertThat(genero).isEmpty
    }

}