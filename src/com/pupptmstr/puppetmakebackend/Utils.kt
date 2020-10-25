package com.pupptmstr.puppetmakebackend

import java.time.LocalDate
import java.util.*

class Utils {
    fun getListFromSqlArray(array: java.sql.Array) : List<String> {
        val result =  array.array as Array<String>
        return result.toList()
    }

    fun getLocalDateFromString(dateTime: String?) : LocalDate? {
        if (dateTime != null) {
            val splitDate = dateTime.split("-")
            val year = splitDate[0]
            val month = splitDate[1]
            val day = splitDate[2].split(" ")[0]
            return LocalDate.of(year.toInt(), month.toInt(), day.toInt())
        } else {
            return null
        }
    }
}