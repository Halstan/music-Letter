package com.musicletter.app.repository

import com.musicletter.app.entity.Genero
import org.springframework.data.jpa.repository.JpaRepository

interface GeneroRepository : JpaRepository<Genero, Int>{

}