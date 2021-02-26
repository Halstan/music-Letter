package com.musicletter.app.entity

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "confirmationToken")
data class ConfirmationToken constructor (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 100)
    private var tokenid: Long? = null,

    @Column(length = 100)
    var confirmationToken: String? = null,

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    val createdDate: Date? = null,

    @OneToOne(targetEntity = Usuario::class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "idUsuario")
    val usuario: Usuario? = null,

    var estado: Boolean? = false

    ){

    @PrePersist
    fun prePersist() {
        estado = true
        confirmationToken = UUID.randomUUID().toString()
    }

}