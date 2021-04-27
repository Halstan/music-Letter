package com.musicletter.app.security

import com.musicletter.app.repository.PermisoRepository
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import java.io.Serializable

class CustomPermissionEvaluator: PermissionEvaluator {

    override fun hasPermission(authentication: Authentication?, targetDomainObject: Any?, permission: Any?): Boolean {
        val permiso = permission.toString()
        return authentication!!.authorities.any {
            it.authority.equals(permiso)
        }
    }

    override fun hasPermission(
        authentication: Authentication?,
        targetId: Serializable?,
        targetType: String?,
        permission: Any?
    ): Boolean {
        return false
    }
}