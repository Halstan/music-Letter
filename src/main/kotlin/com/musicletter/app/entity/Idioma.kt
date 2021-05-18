package com.musicletter.app.entity

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "idiomas")
@Audited
class Idioma : Auditoria() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idIdioma: Int? = null

    @Column(length = 30, nullable = false)
    var nombre: String? = null

}