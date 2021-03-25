package com.musicletter.app.controller

import com.musicletter.app.entity.Cancion
import com.musicletter.app.entity.Idioma
import com.musicletter.app.mapper.CancionMapperImpl
import com.musicletter.app.service.CancionService
import com.musicletter.app.service.ReportService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletResponse
import com.musicletter.app.exception.ReportException
import com.musicletter.app.report.CancionReport
import org.springframework.http.*


@RestController
@RequestMapping("/canciones")
class CancionController (
    val cancionService: CancionService,
    val cancionMapper: CancionMapperImpl,
    val reportService: ReportService
    ){

    companion object {
        const val type = "application/json;charset=UTF-8"
    }

    @GetMapping(produces = [type])
    private fun findAll(): ResponseEntity<*> {
        val canciones = this.cancionService.buscarTodos()
        return ResponseEntity(cancionMapper.toCancionDTOs(canciones), HttpStatus.OK)
    }

    @RequestMapping(method = [RequestMethod.POST, RequestMethod.PUT], produces = [type])
    private fun manipularCancion(@RequestBody cancion: Cancion): ResponseEntity<*> {
        return ResponseEntity(cancionMapper.toCancionDTO(this.cancionService.manipularCancion(cancion)), HttpStatus.CREATED)
    }

    @GetMapping(value = ["{idCancion}"])
    private fun buscarPorId(@PathVariable idCancion: String): ResponseEntity<*> {
        val cancion: Optional<Cancion> = this.cancionService.buscarPorId(idCancion);
        return if (cancion.isPresent) ResponseEntity(cancionMapper.toCancionDTO(cancion.get()), HttpStatus.OK)
        else ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
    }

    @GetMapping(value = ["/usuario"], produces = [type])
    private fun buscarPorUsuario(): ResponseEntity<*> {
        val canciones = this.cancionService.buscarCancionesPorUsuario()
        return ResponseEntity(cancionMapper.toCancionDTOs(canciones), HttpStatus.OK)
    }

    @GetMapping(value = ["/album/{idAlbum}"])
    private fun buscarPorIdAlbum(@PathVariable idAlbum: Int): ResponseEntity<*> {
        val canciones = this.cancionService.buscarCancionesPorAlbum(idAlbum)
        return ResponseEntity(this.cancionMapper.toCancionDTOs(canciones), HttpStatus.OK)
    }

    @GetMapping(value = ["/nombre/{nombre}"])
    private fun buscarPorNombre(@PathVariable nombre: String): ResponseEntity<*> {
        val canciones = this.cancionService.buscarCancionPorNombre(nombre)
        return ResponseEntity(this.cancionMapper.toCancionDTOs(canciones), HttpStatus.OK)
    }

    @GetMapping(value = ["/reporte"], produces = [MediaType.APPLICATION_PDF_VALUE])
    private fun getReport(response: HttpServletResponse): ResponseEntity<*>{
        val canciones = this.cancionService.buscarTodos()
        val cancionReport = CancionReport(this.cancionMapper.toCancionDTOs(canciones))
        val data = this.reportService.getReportPdf(cancionReport.getReport())

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_PDF
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=Reporte de canciones.pdf")
        headers.contentLength = data!!.size.toLong()

        return ResponseEntity(data, headers, HttpStatus.OK)
    }

}