package com.musicletter.app.entity

import org.hibernate.envers.Audited
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "canciones")
@Audited
class Cancion: Auditoria() {

    @Id
    var idCancion: String? = null

    @Column(length = 40, nullable = false)
    val nombre: String? = null

    @Temporal(TemporalType.DATE)
    val fechaLanzamiento: Date? = null

    @ManyToOne
    @JoinColumn(name = "idIdioma")
    val idioma: Idioma? = null

    @Column(length = 5000)
    @Lob
    val letra: String? = null

    @Column(length = 200)
    val urlVideo: String? = null

    @Enumerated(EnumType.ORDINAL)
    val estadoCancion: Estado = Estado.ACTIVO

    @ManyToOne
    @JoinColumn(name = "idAlbum")
    val album: Album? = null

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    var usuario: Usuario? = null

    @PrePersist
    fun init(){
        idCancion = UUID.randomUUID().toString()
    }

}