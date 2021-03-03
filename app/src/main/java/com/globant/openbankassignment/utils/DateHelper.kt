package com.globant.openbankassignment.utils

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

    fun calculateHash(hashString: String): String? {
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest = MessageDigest
                .getInstance(MD5)
            digest.update(hashString.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }


}