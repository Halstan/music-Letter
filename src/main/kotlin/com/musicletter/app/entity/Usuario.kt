package com.musicletter.app.entity

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "usuarios")
@Audited
class Usuario : Auditoria() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idUsuario: Int? = null

    @Column(length = 30)
    val nombres: String? = null

    @Column(length = 50)
    val apellidos: String? = null

    @Column(length = 50, unique = true, nullable = false)
    val nombreDeUsuario: String? = null

    @Column(length = 90, nullable = false)
    val contrasenha: String? = null

    val estado: Boolean? = null

    @ManyToOne
    @JoinColumn(name = "idRol")
    val rol: Rol? = null

}