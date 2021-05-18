package com.musicletter.app.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.envers.Audited
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "albumes")
@Audited
class Album : Auditoria() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAlbum: Int? = null

    @ManyToOne
    @JoinColumn(name = "idAutor")
    var autor: Autor? = null

    @ManyToOne
    @JoinColumn(name = "idGenero")
    var genero: Genero? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @JoinTable(name="album_genero", joinColumns = [JoinColumn(name="idAlbum")], inverseJoinColumns = [JoinColumn(name="idGenero")],
        uniqueConstraints = [UniqueConstraint(columnNames = ["idAlbum", "idGenero"])])
    var subGeneros: Set<Genero>? = null

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    val canciones: Set<Cancion>? = null

    @Column(length = 50, nullable = false)
    var nombre: String? = null

    @Temporal(TemporalType.DATE)
    val fechaLanzamiento: Date? = null

    override fun toString(): String {
        return "Album(idAlbum=$idAlbum, autor=$autor, genero=$genero, subGeneros=$subGeneros, nombre=$nombre, fechaLanzamiento=$fechaLanzamiento)"
    }

}