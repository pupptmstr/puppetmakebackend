package com.pupptmstr.puppetmakebackend.models.responses

import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(
    @SerializedName("array_data")
    val ArrayData: List<T>
)