package com.musicletter.app.report

import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport

interface Report {

    fun getReport(): JasperPrint
}