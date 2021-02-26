package com.musicletter.app.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class Auditoria {

    /*@CreatedBy
    @Column(updatable = false)
    protected var createdBy: String? = null*/

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var fechaCreacion: Date? = Date()

    /*@LastModifiedBy
    protected var lastUpdateBy: String? = null*/

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    var fechaActualizacion: Date? = Date()

}