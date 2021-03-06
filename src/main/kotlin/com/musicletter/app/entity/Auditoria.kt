package com.musicletter.app.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class Auditoria {

    @CreatedBy
    @Column(updatable = false)
    var creadoPor: String? = null

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var fechaCreacion: Date? = Date()

    @LastModifiedBy
    var ultimaActualizacion: String? = null

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    var fechaActualizacion: Date? = Date()

}