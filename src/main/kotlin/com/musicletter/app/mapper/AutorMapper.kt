package com.musicletter.app.mapper

import com.musicletter.app.dto.AutorDTO
import com.musicletter.app.entity.Autor
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AutorMapper {

    fun toAutorDTOs(autores: List<Autor>): List<AutorDTO>

    fun toAutorDTO(autor: Autor): AutorDTO

}