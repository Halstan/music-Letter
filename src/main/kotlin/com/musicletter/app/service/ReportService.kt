package com.musicletter.app.service

import com.musicletter.app.exception.ReportException
import org.springframework.stereotype.Service
import net.sf.jasperreports.engine.JRException

import net.sf.jasperreports.engine.JasperExportManager

import net.sf.jasperreports.engine.JasperPrint

import java.io.IOException

import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput

import net.sf.jasperreports.export.SimpleExporterInput

import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter
import java.io.ByteArrayOutputStream


@Service
class ReportService {

    @Throws(ReportException::class)
    fun getReportPdf(jasperPrint: JasperPrint?): ByteArray? {
        return try {
            JasperExportManager.exportReportToPdf(jasperPrint)
        } catch (e: JRException) {
            throw ReportException(e.message!!)
        }
    }

    @Throws(ReportException::class)
    fun getReportXlsx(jasperPrint: JasperPrint?): ByteArray? {
        val xlsxExporter = JRXlsxExporter()
        var rawBytes: ByteArray
        try {
            ByteArrayOutputStream().use { xlsReport ->
                xlsxExporter.setExporterInput(SimpleExporterInput(jasperPrint))
                xlsxExporter.exporterOutput = SimpleOutputStreamExporterOutput(xlsReport)
                xlsxExporter.exportReport()
                rawBytes = xlsReport.toByteArray()
            }
        } catch (e: JRException) {
            throw ReportException(e)
        } catch (e: IOException) {
            throw ReportException(e)
        }
        return rawBytes
    }

}