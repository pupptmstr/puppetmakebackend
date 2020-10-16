package com.pupptmstr.puppetmakebackend.models

import com.google.gson.annotations.SerializedName

data class Project (
    @SerializedName("id")
    val id: Long,

    @SerializedName("description")
    val description: String,

    @SerializedName("genres")
    val genres: List<String>,

    @SerializedName("project_name")
    val projectName: String,

    @SerializedName("status")//статус готовности проекта (t- релиз; f-в разработке)
    val status: Boolean,

    @SerializedName("tech_specs")
    val techSpecs: String,

    @SerializedName("logo_img_link")
    val logoImgLink: String
)
