package com.musicletter.app.security

import com.musicletter.app.exception.AuthException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices

@Configuration
@EnableResourceServer
class ResourceServerConfig(
    val tokenService: ResourceServerTokenServices
    ): ResourceServerConfigurerAdapter() {

    @Value("\${security.jwt.resource-ids}")
    private val resourceIds: String? = null

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources!!.resourceId(resourceIds).tokenServices(this.tokenService)
    }

    override fun configure(http: HttpSecurity) {
        http
            .exceptionHandling().authenticationEntryPoint(AuthException())
            .and()
            .requestMatchers()
            .and()
            .authorizeRequests()
            .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**").permitAll()
            // TODO
            .antMatchers("/api/**" ).permitAll();
    }



}