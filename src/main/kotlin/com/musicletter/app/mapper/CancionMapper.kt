package com.musicletter.app.mapper

import com.musicletter.app.dto.CancionDTO
import com.musicletter.app.entity.Cancion
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CancionMapper {

    fun toCancionDTOs(canciones: List<Cancion>): List<CancionDTO>

    fun toCancionDTO(cancion: Cancion): CancionDTO

}