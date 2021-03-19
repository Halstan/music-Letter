package com.musicletter.app.report

import com.musicletter.app.exception.ReportException
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport

interface Report {

    @Throws(ReportException::class)
    fun getReport(): JasperPrint
}