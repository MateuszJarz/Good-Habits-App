package com.example.goodhabitsapp.util

import java.util.concurrent.TimeUnit

object Utility {
    //time - 1min - 60secs

    const val TIME_COUNTDOWN: Long = 60000L * 25 // 25 min
    private const val TIME_FORMAT = "%02d:%02d"


    //convert time to milli seconds
    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}