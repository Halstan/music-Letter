package com.musicletter.app.mapper

import com.musicletter.app.dto.AlbumDTO
import com.musicletter.app.entity.Album
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AlbumMapper {

    fun toAlbumDTOs(albumes: List<Album>): List<AlbumDTO>

    fun toAlbumDTO(album: Album): AlbumDTO

}