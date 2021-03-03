package com.musicletter.app.mapper

import com.musicletter.app.dto.UsuarioDTO
import com.musicletter.app.entity.Usuario
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UsuarioMapper {

    fun toUsuarioDTOs(usuarios: List<Usuario>): List<UsuarioDTO>

    fun toUsuarioDTO(usuario: Usuario): UsuarioDTO

}