package com.musicletter.app.entity

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "permisos")
@Audited
class Permiso : Auditoria() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idPermiso: Int? = null

    @Column(length = 30)
    val nombre: String? = null

}