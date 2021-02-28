package com.musicletter.app.dto

import java.util.*

class AlbumDTO {

    var idAlbum: Int? = null

    var autor: AutorDTO? = null

    var genero: GeneroDTO? = null

    var subGeneros: Set<GeneroDTO>? = null

    var nombre: String? = null

    var fechaLanzamiento: Date? = null

}