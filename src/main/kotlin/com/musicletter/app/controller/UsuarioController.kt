package com.musicletter.app.controller

import com.musicletter.app.entity.ConfirmationToken
import com.musicletter.app.entity.Usuario
import com.musicletter.app.mapper.UsuarioMapper
import com.musicletter.app.mapper.UsuarioMapperImpl
import com.musicletter.app.service.ConfirmationTokenService
import com.musicletter.app.service.EmailService
import com.musicletter.app.service.UsuarioService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
@Validated
class UsuarioController(
    val usuarioService: UsuarioService,
    val emailService: EmailService,
    val confirmationTokenService: ConfirmationTokenService,
    val usuarioMapper: UsuarioMapperImpl
    ){

    companion object {
        const val type = "application/json;charset=UTF-8"
    }

    @GetMapping(produces = [type])
    private fun buscarTodos(): ResponseEntity<*> {
        val usuarios = this.usuarioService.buscarTodos()
        return ResponseEntity(this.usuarioMapper.toUsuarioDTOs(usuarios), HttpStatus.OK)
    }

    @PostMapping(produces = [type])
    private fun registrar(@RequestBody usuario: Usuario): ResponseEntity<*> {
        this.usuarioService.registro(usuario)

        val confirmationToken = ConfirmationToken(usuario)
        this.confirmationTokenService.save(confirmationToken)

        val templateModel: MutableMap<String, Any> = HashMap()
        templateModel["recipientName"] = "${usuario.nombres} ${usuario.apellidos}"
        templateModel["text"] = "http://localhost:7050/api/back/confirmar/cuenta/${confirmationToken.confirmationToken}"
        templateModel["senderName"] = "enzoarauco@gmail.com"

        this.emailService.sendMessageUsingThymeleafTemplate(
            usuario.correo!!,
            "Correo de prueba",
            templateModel)

        return ResponseEntity(this.usuarioMapper.toUsuarioDTO(usuario), HttpStatus.CREATED)
    }

}