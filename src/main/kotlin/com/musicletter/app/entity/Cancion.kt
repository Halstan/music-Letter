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

    @Column(length = 40)
    val nombre: String? = null

    @Temporal(TemporalType.DATE)
    val fechaLanzamiento: Date? = null

    @ManyToOne
    @JoinColumn(name = "idIdioma")
    val idioma: Idioma? = null

    @ManyToOne
    @JoinColumn(name = "idAlbum")
    val album: Album? = null

    @PrePersist
    fun init(){
        idCancion = UUID.randomUUID().toString()
    }

}