package com.musicletter.app.controller

import com.musicletter.app.service.ConfirmationTokenService
import com.musicletter.app.service.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/confirmar")
class ConfirmationTokenController(
    val confirmationTokenService: ConfirmationTokenService,
    val usuarioService: UsuarioService
    ){

    @GetMapping(value = ["/cuenta/{token}"])
    private fun confirmarCuenta(@PathVariable token: String): ResponseEntity<*>{
        val response: MutableMap<String, Any> = HashMap()
        val confirmationToken = this.confirmationTokenService.findByConfirmationToken(token)

        return if (confirmationToken.isPresent){
            this.usuarioService.activarUsuario(confirmationToken.get().usuario!!.nombreDeUsuario!!)
            this.confirmationTokenService.desactivarToken(token)
            response["message"] = "Cuenta verificada correctamente"
            ResponseEntity(response, HttpStatus.OK)
        } else {
            response["error"] = "Token expirado y/o no encontrado"
            ResponseEntity(response, HttpStatus.BAD_REQUEST)
        }

    }

}