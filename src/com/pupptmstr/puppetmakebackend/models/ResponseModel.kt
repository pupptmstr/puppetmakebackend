package com.pupptmstr.puppetmakebackend.models

data class ResponseModel<T>(
    val ArrayData: List<T>
)