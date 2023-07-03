package com.srgrsj.tyb.util

object DateTimeUtils {
    fun formatDuration(durationMillis: Long): String {
        val seconds = (durationMillis / 1000) % 60
        val minutes = (durationMillis / (1000 * 60)) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}