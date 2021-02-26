package com.musicletter.app.entity

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "roles")
@Audited
data class Rol constructor (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idRol: Int,

    @Column(length = 30, nullable = false)
    val nombre: String

    ) : Auditoria() {
}