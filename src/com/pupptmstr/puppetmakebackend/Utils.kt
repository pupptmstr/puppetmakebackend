package com.pupptmstr.puppetmakebackend

import java.time.LocalDate

class Utils {
    fun getListFromSqlArray(array: java.sql.Array) : List<String>? {
        return if (array != null) {
            val result =  array.array as Array<String>
            result.toList()
        } else {
            null
        }
    }

    fun getLocalDateFromString(dateTime: String?) : LocalDate? {
        return if (dateTime != null) {
            val splitDate = dateTime.split("-")
            val year = splitDate[0]
            val month = splitDate[1]
            val day = splitDate[2].split(" ")[0]
            LocalDate.of(year.toInt(), month.toInt(), day.toInt())
        } else {
            null
        }
    }
}