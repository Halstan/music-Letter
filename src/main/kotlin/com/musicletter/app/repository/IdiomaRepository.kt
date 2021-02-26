package com.musicletter.app.repository

import com.musicletter.app.entity.Idioma
import org.springframework.data.jpa.repository.JpaRepository

interface IdiomaRepository : JpaRepository<Idioma, Int>{
}