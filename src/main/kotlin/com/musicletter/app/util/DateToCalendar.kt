package com.musicletter.app.util

import java.util.*

class DateToCalendar {

    fun dateToCalendar(date: Date): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    //Convert Calendar to Date
    fun calendarToDate(calendar: Calendar): Date {
        return calendar.time
    }

}