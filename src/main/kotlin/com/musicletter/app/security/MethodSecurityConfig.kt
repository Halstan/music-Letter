package com.musicletter.app.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

@Configuration
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true)
class MethodSecurityConfig: GlobalMethodSecurityConfiguration(){

    override fun createExpressionHandler(): MethodSecurityExpressionHandler {
        val meh = DefaultMethodSecurityExpressionHandler();
        meh.setPermissionEvaluator(permissionEvaluator())
        return meh
    }

    private fun permissionEvaluator(): PermissionEvaluator {
        return CustomPermissionEvaluator();
    }
}