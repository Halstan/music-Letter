package com.musicletter.app.service

import com.musicletter.app.entity.Permiso
import com.musicletter.app.entity.Rol
import com.musicletter.app.entity.Usuario
import com.musicletter.app.repository.UsuarioRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors


@Service
class UsuarioService (
    val usuarioRepository: UsuarioRepository
    ) : UserDetailsService {

    @Transactional(readOnly = true)
    fun buscarTodos(): List<Usuario> =
        this.usuarioRepository.findAll()

    @Transactional
    fun registro(usuario: Usuario): Usuario {
        usuario.contrasenha = BCryptPasswordEncoder().encode(usuario.contrasenha)
        usuario.rol = Rol(2)
        return this.usuarioRepository.save(usuario)
    }

    @Transactional
    fun activarUsuario(nombreDeUsuario: String): Usuario {
        val usuario = this.usuarioRepository.findByNombreDeUsuarioIgnoreCase(nombreDeUsuario)
        return if (usuario.isPresent){
            this.usuarioRepository.confirmarCuenta(nombreDeUsuario)
            usuario.get()
        } else Usuario()
    }

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
                //getAuthorities(listOf(usuario.get().rol!!))
                listOf(usuario.get().rol!!).stream().map { rol ->
                    SimpleGrantedAuthority(
                        rol.nombre
                    )
                }.collect(Collectors.toList())
            )
        } else throw UsernameNotFoundException("Nombre de usuario no existente")
    }

    private fun getAuthorities(roles: List<Rol>): List<GrantedAuthority> {
        return getGrantedAuthorities(getPrivileges(roles))
    }

    private fun getPrivileges(roles: List<Rol>): List<String> {
        val privileges: MutableList<String> = ArrayList()
        val permisos: MutableSet<Permiso> = mutableSetOf()
        for (rol in roles) {
            rol.permisos?.let { permisos.addAll(it) }
        }
        for (permiso in permisos) {
            privileges.add(permiso.nombre!!)
        }
        return privileges
    }

    private fun getGrantedAuthorities(privileges: List<String>): List<GrantedAuthority> {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        for (privilege in privileges) {
            authorities.add(SimpleGrantedAuthority(privilege))
        }
        return authorities
    }
}