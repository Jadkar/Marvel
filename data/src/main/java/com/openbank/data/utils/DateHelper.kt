package com.openbank.data.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun getCurrentTimestamp(): String? {
        return SimpleDateFormat("yyyy-MM-dd HH:mm").format(
            Calendar
                .getInstance().time
        )
    }
}