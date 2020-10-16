package com.pupptmstr.puppetmakebackend.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class News(
        @SerializedName("id")
        val id: Long,

        @SerializedName("header")
        val header: String,

        @SerializedName("content")
        val content: String,

        @SerializedName("create_at")
        val createAt: LocalDate,

        @SerializedName("delete_at")
        val deleteAt: LocalDate,

        @SerializedName("main_image_link")
        val mainImageLink: String
)