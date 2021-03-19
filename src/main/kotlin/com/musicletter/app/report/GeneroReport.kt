package com.musicletter.app.report

import ar.com.fdvs.dj.core.DynamicJasperHelper
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager
import ar.com.fdvs.dj.domain.DynamicReport
import ar.com.fdvs.dj.domain.builders.ColumnBuilder
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder
import ar.com.fdvs.dj.domain.builders.StyleBuilder
import ar.com.fdvs.dj.domain.constants.Font
import ar.com.fdvs.dj.domain.constants.HorizontalAlign
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn
import com.musicletter.app.dto.GeneroDTO
import com.musicletter.app.exception.ReportException
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import java.util.*

import net.sf.jasperreports.engine.JRException

class GeneroReport(generos: List<GeneroDTO>): Report {

    private var generos: List<GeneroDTO> = generos

    @Throws(ReportException::class)
    override fun getReport(): JasperPrint {
        val jp: JasperPrint
        jp = try {
            val dynaReport: DynamicReport = getReports()
            DynamicJasperHelper.generateJasperPrint(
                dynaReport, ClassicLayoutManager(),
                JRBeanCollectionDataSource(generos)
            )
        } catch (e: JRException) {
            throw ReportException(e.message!!)
        } catch (e: ColumnBuilderException) {
            throw ReportException(e.message!!)
        } catch (e: ClassNotFoundException) {
            throw ReportException(e.message!!)
        }

        return jp
    }

    @Throws(ColumnBuilderException::class)
    private fun createColumn(
        property: String,
        type: Class<*>,
        title: String,
        width: Int
    ): AbstractColumn? {
        return ColumnBuilder.getNew()
            .setColumnProperty(property, type.name)
            .setTitle(title)
            .setWidth(Integer.valueOf(width))
            .build()
    }
    
    private fun getReports(): DynamicReport {
        val report = DynamicReportBuilder()

        val columnId = createColumn("id", String::class.java, "Nombre", 30)!!
        val columnNombre = createColumn("colNo", Date::class.java, "Fecha de Lanzamiento", 30)

        report.addColumn(columnId).addColumn(columnNombre)

        report.setTitle("Reportes de canciones")
        report.setSubtitle("Comision de ejemplo")
        report.setUseFullPageWidth(true)

        return report.build()
    }
    
}