package com.musicletter.app.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import java.lang.Exception
import org.springframework.security.oauth2.provider.token.DefaultTokenServices

import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
    val tokenStore: TokenStore,
    val accessTokenConverter: JwtAccessTokenConverter,
    val authenticationManager: AuthenticationManager,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
    val dataSource: DataSource
    ): AuthorizationServerConfigurerAdapter() {

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        val enhancerChain = TokenEnhancerChain()
        enhancerChain.setTokenEnhancers(listOf(accessTokenConverter))
        endpoints!!.tokenStore(tokenStore)
            .accessTokenConverter(accessTokenConverter)
            .reuseRefreshTokens(false)
            .tokenEnhancer(enhancerChain)
            .authenticationManager(authenticationManager)
            .pathMapping("/oauth/token", "/login")
    }

    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.passwordEncoder(BCryptPasswordEncoder())
    }

    @Bean
    @Primary
    fun tokenServices(): DefaultTokenServices? {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore)
        defaultTokenServices.setSupportRefreshToken(true)
        return defaultTokenServices
    }

}