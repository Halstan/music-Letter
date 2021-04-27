package com.musicletter.app.entity

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "roles")
@Audited
class Rol(idRol: Int) : Auditoria() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idRol: Int = idRol

    @Column(length = 30, nullable = false)
    val nombre: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RolPermiso", joinColumns = [JoinColumn(name = "idRol")], inverseJoinColumns = [JoinColumn(name = "idPermiso")])
    var permisos: Set<Permiso>? = null

}