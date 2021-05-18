package com.musicletter.app.service

import com.musicletter.app.entity.Idioma
import com.musicletter.app.repository.IdiomaRepository
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class IdiomaServiceTest{

    @Mock
    lateinit var idiomaRepository: IdiomaRepository
    private lateinit var underTest: IdiomaService

    @BeforeEach
    internal fun setUp() {
        underTest = IdiomaService(this.idiomaRepository)
    }

    @Test
    fun canBuscarTodos() {
        // WHEN
        underTest.buscarTodos()

        // THEN
        verify(idiomaRepository).findAll()
    }

    @Test
    fun canManipularIdioma() {
        // GIVEN
        val idioma = Idioma()
        idioma.nombre = "Quechua"

        // WHEN
        underTest.manipularIdioma(idioma)

        // THEN
        val argumentCaptor = ArgumentCaptor.forClass(Idioma::class.java)
        verify(idiomaRepository).save(argumentCaptor.capture())

        val capturedIdioma = argumentCaptor.value

        assertThat(capturedIdioma).isEqualTo(idioma)
    }

    @Test
    @Disabled
    fun buscarIdioma() {
    }
}