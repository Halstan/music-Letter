package com.musicletter.app.entity

import com.fasterxml.jackson.annotation.JsonValue

enum class Estado (private val mensaje: String) {

    ACTIVO ("Activo"),
    EDITADO ("Editado"),
    ELIMINADO ("Eliminado");

    @JsonValue
    fun getName(): String {
        return mensaje;
    }

}