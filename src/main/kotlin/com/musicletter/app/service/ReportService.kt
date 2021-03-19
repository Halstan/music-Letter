package com.musicletter.app.service

import com.musicletter.app.exception.ReportException
import org.springframework.stereotype.Service
import net.sf.jasperreports.engine.JRException

import net.sf.jasperreports.engine.JasperExportManager

import net.sf.jasperreports.engine.JasperPrint

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

}