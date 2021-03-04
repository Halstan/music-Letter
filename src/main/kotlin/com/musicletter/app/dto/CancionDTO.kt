package com.musicletter.app.dto

import com.musicletter.app.entity.Estado
import java.util.*

class CancionDTO {

    var idCancion: String? = null

    var nombre: String? = null

    var fechaLanzamiento: Date? = null

    var idioma: IdiomaDTO? = null

    var letra: String? = null

    var urlVideo: String? = null

    var estadoCancion: Estado? = null

    var album: AlbumDTO? = null

    var usuario: UsuarioDTO? = null

}