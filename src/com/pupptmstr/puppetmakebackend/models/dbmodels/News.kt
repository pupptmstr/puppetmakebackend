package com.pupptmstr.puppetmakebackend.models.dbmodels

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class News(
        @SerializedName("id")
        var id: Long,

        @SerializedName("header")
        var header: String,

        @SerializedName("content")
        var content: String,

        @SerializedName("create_at")
        var createAt: LocalDate,

        @SerializedName("delete_at")
        var deleteAt: LocalDate? = null,

        @SerializedName("main_image_link")
        var mainImageLink: String
)