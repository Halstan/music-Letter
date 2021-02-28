package com.musicletter.app.mapper

import com.musicletter.app.dto.IdiomaDTO
import com.musicletter.app.entity.Idioma
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface IdiomaMapper {

    fun toIdiomaDTOs(idiomas: List<Idioma>): List<IdiomaDTO>

    fun toIdiomaDTO(idioma: Idioma): IdiomaDTO

}