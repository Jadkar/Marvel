package com.openbank.data.data.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
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