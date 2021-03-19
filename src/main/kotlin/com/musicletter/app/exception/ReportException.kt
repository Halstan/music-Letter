package com.musicletter.app.exception

import java.lang.Exception

class ReportException : Exception {
    constructor() {}
    constructor(message: String?) : super(message) {}
    constructor(cause: Throwable?) : super(cause) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        message,
        cause,
        enableSuppression,
        writableStackTrace
    ) {
    }

    companion object {
        private const val serialVersionUID = -4687377061480077553L
    }
}
