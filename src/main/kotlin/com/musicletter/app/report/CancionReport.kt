package com.musicletter.app.report

import ar.com.fdvs.dj.core.DynamicJasperHelper
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager
import ar.com.fdvs.dj.domain.DynamicReport
import ar.com.fdvs.dj.domain.Style
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder
import ar.com.fdvs.dj.domain.builders.StyleBuilder
import ar.com.fdvs.dj.domain.constants.*
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn
import net.sf.jasperreports.engine.JasperPrint

import java.awt.Color
import ar.com.fdvs.dj.domain.builders.ColumnBuilder

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException
import java.util.*
import ar.com.fdvs.dj.domain.constants.HorizontalAlign
import com.musicletter.app.dto.CancionDTO
import com.musicletter.app.exception.ReportException
import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

class CancionReport(canciones: List<CancionDTO> ): Report {

    private var canciones: List<CancionDTO>? = canciones

    override fun getReport(): JasperPrint {
        var  jp: JasperPrint? = JasperPrint()
        try {
            val headerStyle = createHeaderStyle()
            val detailTextStyle = createDetailTextStyle()
            val detailNumberStyle = createDetailNumberStyle()
            val dynaReport: DynamicReport = getReports(headerStyle!!, detailTextStyle!!, detailNumberStyle!!)
            jp = DynamicJasperHelper.generateJasperPrint(dynaReport, ClassicLayoutManager(), JRBeanCollectionDataSource(canciones))
        }catch(e: JRException){
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
            .setBackgroundColor(Color.ORANGE)
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

        val columnNombre: AbstractColumn = createColumn("nombre", String::class.java, "Nombre", 30, headerStyle, detailTextStyle)!!
        val columnFechaLanzamiento = createColumn("fechaLanzamiento", Date::class.java, "Fecha de Lanzamiento", 30, headerStyle, detailTextStyle)
        val columnIdioma = createColumn("idioma.nombre", String::class.java, "Idioma", 30, headerStyle, detailTextStyle)
        val columnVideo = createColumn("urlVideo", String::class.java, "Url del video", 30, headerStyle, detailTextStyle)
        val columnAlbum = createColumn("album.nombre", String::class.java, "Album", 30, headerStyle, detailTextStyle)
        val columnUsuario = createColumn("usuario.nombreDeUsuario", String::class.java, "Usuario", 30, headerStyle, detailTextStyle)

        report.addColumn(columnNombre).addColumn(columnFechaLanzamiento).addColumn(columnIdioma).addColumn(columnVideo).addColumn(columnAlbum).addColumn(columnUsuario)

        val titleStyle = StyleBuilder(true)
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER)
        titleStyle.setFont(Font(20, null, true))

        val subTitleStyle = StyleBuilder(true)
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER)
        subTitleStyle.setFont(Font(Font.MEDIUM, null, true))

        report.setTitle("Reportes de canciones")
        report.setTitleStyle(titleStyle.build())
        report.setSubtitle("Reporte de canciones")
        report.setSubtitleStyle(subTitleStyle.build())
        report.setUseFullPageWidth(true)

        return report.build()
    }
}