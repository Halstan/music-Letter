package com.musicletter.app.security

import com.musicletter.app.service.UsuarioService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import javax.sql.DataSource


@EnableWebSecurity
@Configuration
class SecurityConfig (
    private val dataSource: DataSource,
    private val usuarioService: UsuarioService
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
        auth.authenticationProvider(authenticationProvider())
            .userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder())
    }

    /*@Bean
    fun authenticationProvider(): DaoAuthenticationProvider? {
        val provider = CustomAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(this.usuarioService)
        return provider
    }*/

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .logout()
            .clearAuthentication(true)
            .and()
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
        return JdbcTokenStores(dataSource)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter? {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey(signingKey)
        return converter
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val dap = DaoAuthenticationProvider()
        dap.setUserDetailsService(this.usuarioService)
        dap.setPasswordEncoder(passwordEncoder())
        return dap
    }

}