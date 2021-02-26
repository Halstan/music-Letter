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
    val nombres: String? = null

    @Column(length = 40)
    val apellidos: String? = null

    @Temporal(TemporalType.DATE)
    val fechaNacimiento: Date = Date()

    @Column(length = 40, nullable = false)
    val alias: String? = null

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    val albumes: Set<Album>? = null

}