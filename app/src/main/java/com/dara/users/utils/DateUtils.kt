package com.dara.users.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    // Formats user's date of birth in readable form
    fun formatDate(timeString: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return formatter.format(parser.parse(timeString))
    }
}