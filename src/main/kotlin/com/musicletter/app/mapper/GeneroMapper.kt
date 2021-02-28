package com.musicletter.app.mapper

import com.musicletter.app.dto.GeneroDTO
import com.musicletter.app.entity.Genero
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GeneroMapper {

    fun toGeneroDTOs(generos: List<Genero>): List<GeneroDTO>

    fun toGeneroDTO(genero: Genero): GeneroDTO

}