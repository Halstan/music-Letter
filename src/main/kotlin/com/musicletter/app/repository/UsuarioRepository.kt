package com.musicletter.app.repository

import com.musicletter.app.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UsuarioRepository : JpaRepository<Usuario, Int>{

    fun findByNombreDeUsuarioIgnoreCase(username: String?): Optional<Usuario?>?

    @Modifying
    @Query("UPDATE Usuario u set u.estado = true where u.nombreDeUsuario = ?1")
    fun confirmarCuenta(username: String): Int?

    @Modifying
    @Query("UPDATE Usuario u set u.contrasenha = :password where u.contrasenha = :username")
    fun changePassword(password: String?, username: String?)

}