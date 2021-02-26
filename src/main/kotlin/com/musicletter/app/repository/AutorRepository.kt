package com.musicletter.app.repository

import com.musicletter.app.entity.Autor
import org.springframework.data.jpa.repository.JpaRepository

interface AutorRepository : JpaRepository<Autor, Int>{
}