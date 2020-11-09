package com.pupptmstr.puppetmakebackend.models.dbmodels

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Teammate(
    @SerializedName("id")
    val id: Long,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("surname")
    val surname: String,

    @SerializedName("nickname")
    val nickname: String?,

    @SerializedName("hired_at")
    val hiredAt: LocalDate,

    @SerializedName("global_role")
    val globalRole: String,

    @SerializedName("current_role")
    val currentRole: String?,

    @SerializedName("main_photo_link")
    val mainPhotoLink: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("social_links")
    val socialLinks: List<String>,

    @SerializedName("photos")
    val photos: List<String>?
)