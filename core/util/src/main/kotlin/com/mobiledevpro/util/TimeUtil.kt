package com.mobiledevpro.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeUtil {

    fun currentTimeStamp(): String = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN).let { format ->
        LocalDateTime.now().format(format)
    }
}

private const val TIMESTAMP_PATTERN = "yyyyMMdd_HHmmssSSS"