package com.musicletter.app.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.musicletter.app.util.DateToCalendar
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.HashMap

class AuthException : AuthenticationEntryPoint{

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val responseError: MutableMap<String, Any> = HashMap()

        responseError["error"] = "401"
        responseError["message"] = "No tienes permiso para acceder a este recurso"
        responseError["exception"] = "No autorizado"
        responseError["tipo"] = request!!.method
        responseError["path"] = request.servletPath
        responseError["timestamp"] = Date()

        response!!.contentType = "application/json"
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        val mapper = ObjectMapper()
        mapper.writeValue(response.outputStream, responseError)

    }
}