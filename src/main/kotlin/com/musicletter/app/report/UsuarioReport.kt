package com.musicletter.app.report

import ar.com.fdvs.dj.core.DynamicJasperHelper
import ar.com.fdvs.dj.core.JasperDesignDecorator
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager
import ar.com.fdvs.dj.domain.DynamicReport
import ar.com.fdvs.dj.domain.Style
import ar.com.fdvs.dj.domain.builders.ColumnBuilder
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder
import ar.com.fdvs.dj.domain.builders.StyleBuilder
import ar.com.fdvs.dj.domain.constants.*
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn
import com.musicletter.app.dto.UsuarioDTO
import com.musicletter.app.entity.Usuario
import com.musicletter.app.exception.ReportException
import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import java.awt.Color
import java.util.*

class UsuarioReport(usuarios: List<Usuario>): Report {

    private var usuarios: List<Usuario>? = usuarios

    override fun getReport(): JasperPrint {
        val jp: JasperPrint?
        try {
            val headerStyle = createHeaderStyle()
            val detailTextStyle = createDetailTextStyle()
            val detailNumberStyle = createDetailNumberStyle()
            val dynaReport: DynamicReport = getReports(headerStyle!!, detailTextStyle!!, detailNumberStyle!!)
            jp = DynamicJasperHelper.generateJasperPrint(dynaReport, ClassicLayoutManager(), JRBeanCollectionDataSource(usuarios))
        }catch (e: JRException){
            throw ReportException(e.message!!)
        } catch (e: ColumnBuilderException) {
            throw ReportException(e.message!!)
        } catch (e: ClassNotFoundException) {
            throw ReportException(e.message!!)
        }
        return jp!!
    }

    private fun createHeaderStyle(): Style? {
        return StyleBuilder(true)
            .setFont(Font.VERDANA_MEDIUM_BOLD)
            .setBorder(Border.THIN())
            .setBorderBottom(Border.PEN_2_POINT())
            .setBorderColor(Color.BLACK)
            .setBackgroundColor(Color.red)
            .setTextColor(Color.BLACK)
            .setHorizontalAlign(HorizontalAlign.CENTER)
            .setVerticalAlign(VerticalAlign.MIDDLE)
            .setTransparency(Transparency.OPAQUE)
            .build()
    }

    private fun createDetailTextStyle(): Style? {
        return StyleBuilder(true)
            .setFont(Font.VERDANA_MEDIUM)
            .setBorder(Border.DOTTED())
            .setBorderColor(Color.BLACK)
            .setTextColor(Color.BLACK)
            .setHorizontalAlign(HorizontalAlign.LEFT)
            .setVerticalAlign(VerticalAlign.MIDDLE)
            .setPaddingLeft(5)
            .build()
    }

    private fun createDetailNumberStyle(): Style? {
        return StyleBuilder(true)
            .setFont(Font.VERDANA_MEDIUM)
            .setBorder(Border.DOTTED())
            .setBorderColor(Color.BLACK)
            .setTextColor(Color.BLACK)
            .setHorizontalAlign(HorizontalAlign.RIGHT)
            .setVerticalAlign(VerticalAlign.MIDDLE)
            .setPaddingRight(5)
            .setPattern("#,##0.00")
            .build()
    }

    @Throws(ColumnBuilderException::class)
    private fun createColumn(
        property: String,
        type: Class<*>,
        title: String,
        width: Int,
        headerStyle: Style,
        detailStyle: Style
    ): AbstractColumn? {
        return ColumnBuilder.getNew()
            .setColumnProperty(property, type.name)
            .setTitle(title)
            .setWidth(Integer.valueOf(width))
            .setStyle(detailStyle)
            .setHeaderStyle(headerStyle)
            .build()
    }

     private fun getReports(headerStyle: Style, detailTextStyle: Style, detailNumStyle: Style): DynamicReport {

         val report = DynamicReportBuilder()

         val columnNombres = createColumn("nombres", String::class.java, "Nombres", 30,
             headerStyle, detailTextStyle)
         val columnApellidos = createColumn("apellidos", String::class.java, "Apellidos", 30,
             headerStyle, detailTextStyle)
         val columnUsername = createColumn("nombreDeUsuario", String::class.java, "Username", 30,
             headerStyle, detailTextStyle)
         val columnCorreo = createColumn("correo", String::class.java, "Username", 30, headerStyle,
             detailTextStyle)
         val columnFechaCreacion = createColumn("fechaCreacion", Date::class.java, "Fecha de creación", 30,
             headerStyle, detailTextStyle)
         val columnFechaActualizacion = createColumn("fechaActualizacion", Date::class.java, "Fecha de actualización", 30,
             headerStyle, detailTextStyle)

        report.addColumn(columnNombres).addColumn(columnApellidos).addColumn(columnUsername)
            .addColumn(columnCorreo).addColumn(columnFechaCreacion).addColumn(columnFechaActualizacion)

         val titleStyle = StyleBuilder(true)
         titleStyle.setHorizontalAlign(HorizontalAlign.CENTER)
         titleStyle.setFont(Font(20, null, true))

         val subTitleStyle = StyleBuilder(true)
         subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER)
         subTitleStyle.setFont(Font(Font.MEDIUM, null, true))

         report.setTitle("Reporte de usuarios")
         report.setTitleStyle(titleStyle.build())
         report.setSubtitle("Reporte generado el ${Date()}")
         report.setSubtitleStyle(subTitleStyle.build())
         report.setUseFullPageWidth(true)

         return report.build()
     }
}