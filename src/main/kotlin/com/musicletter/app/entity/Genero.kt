package com.musicletter.app.entity

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "generos")
@Audited
class Genero: Auditoria() {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idGenero: Int? = null
    
    @Column(length = 30, nullable = false)
    var nombre: String? = null

    override fun toString(): String {
        return "Genero(idGenero=$idGenero, nombre=$nombre)"
    }

}