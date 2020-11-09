package com.pupptmstr.puppetmakebackend.models.responses

import com.google.gson.annotations.SerializedName
import com.pupptmstr.puppetmakebackend.models.dbmodels.News
import com.pupptmstr.puppetmakebackend.models.dbmodels.Project
import com.pupptmstr.puppetmakebackend.models.dbmodels.Teammate

data class SearchResponseModel(
    @SerializedName("search_news")
    val searchNews: ResponseModel<News>,

    @SerializedName("search_teammates")
    val searchTeammate: ResponseModel<Teammate>,

    @SerializedName("search_projects")
    val searchProject: ResponseModel<Project>
)