package com.musicletter.app.security

import com.musicletter.app.service.UsuarioService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import java.lang.Exception
import javax.sql.DataSource

@EnableWebSecurity
@Configuration
class SecurityConfig (
    val dataSource: DataSource,
    val usuarioService: UsuarioService
    ) : WebSecurityConfigurerAdapter(){

    @Value("\${security.signing-key}")
    private val signingKey: String? = null

    @Value("\${security.security-realm}")
    private val securityRealm: String? = null

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder(10)
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManager(): AuthenticationManager? {
        return super.authenticationManager()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic()
            .realmName(securityRealm)
            .and()
            .csrf()
            .disable()
    }

    @Bean
    fun tokenStore(): TokenStore? {
        return JdbcTokenStore(dataSource)
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter? {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey(signingKey)
        return converter
    }

    @Bean
    @Primary
    fun tokenServices(): DefaultTokenServices? {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore())
        defaultTokenServices.setSupportRefreshToken(true)
        defaultTokenServices.setReuseRefreshToken(false)
        return defaultTokenServices
    }

}