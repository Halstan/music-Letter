package com.musicletter.app.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.envers.Audited
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "autores")
@Audited
class Autor : Auditoria() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAutor: Int? = null

    @Column(length = 40)
    var nombres: String? = null

    @Column(length = 40)
    val apellidos: String? = null

    @Column(length = 200)
    val urlFoto: String? = null

    @Temporal(TemporalType.DATE)
    val fechaNacimiento: Date = Date()

    @Column(length = 40, nullable = false)
    var alias: String? = null

    @Column(length = 300)
    val biografia: String? = null

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    val albumes: Set<Album>? = null

    override fun toString(): String {
        return "Autor(idAutor=$idAutor, nombres=$nombres, apellidos=$apellidos, urlFoto=$urlFoto, fechaNacimiento=$fechaNacimiento, alias=$alias, biografia=$biografia)"
    }

}