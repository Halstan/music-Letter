package com.musicletter.app.security

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource
import org.springframework.dao.EmptyResultDataAccessException

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.stereotype.Component

@Component
class JdbcTokenStores(dataSource: DataSource?) : JdbcTokenStore(dataSource) {
    private val log: Log = LogFactory.getLog(JdbcTokenStores::class.java)

    override fun readAccessToken(tokenValue: String?): OAuth2AccessToken {
        var accessToken: OAuth2AccessToken? = null

        try {
            accessToken = DefaultOAuth2AccessToken(tokenValue)
        } catch (e: EmptyResultDataAccessException) {
            if (log.isInfoEnabled) {
                log.info("Failed to find access token for token $tokenValue")
            }
        } catch (e: IllegalArgumentException) {
            log.warn("Failed to deserialize access token for $tokenValue", e)
            removeAccessToken(tokenValue)
        }

        return accessToken!!
    }
}