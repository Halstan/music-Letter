package com.musicletter.app.service

import com.musicletter.app.repository.UsuarioRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class UsuarioService (
    val usuarioRepository: UsuarioRepository
    ) : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        val usuario = this.usuarioRepository.findByNombreDeUsuarioIgnoreCase(username)

        if (usuario.isPresent) {
            return User(
                usuario.get().nombreDeUsuario,
                usuario.get().contrasenha,
                usuario.get().estado!!,
                true,
                true,
                true,
                listOf(usuario.get().rol!!).stream().map { rol ->
                    SimpleGrantedAuthority(
                        rol.nombre
                    )
                }.collect(Collectors.toList())
            )
        } else throw UsernameNotFoundException("Nombre de usuario no existente")
    }
}