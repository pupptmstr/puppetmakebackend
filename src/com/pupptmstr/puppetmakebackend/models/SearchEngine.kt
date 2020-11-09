package com.pupptmstr.puppetmakebackend.models

import com.pupptmstr.puppetmakebackend.dbrepo.NewsRepo
import com.pupptmstr.puppetmakebackend.dbrepo.ProjectsRepo
import com.pupptmstr.puppetmakebackend.dbrepo.TeammatesRepo
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.searchEngine(newsRepo: NewsRepo, teammatesRepo: TeammatesRepo, projectsRepo: ProjectsRepo) {
    route("/search") {
        get() {
            val request = call.request.queryParameters["query"]
            if (!request.isNullOrEmpty() && !request.isNullOrBlank()) {
                //respond
            }
        }

    }
}

fun searchNews():  {

}

fun searchTeammates() : {

}

fun searchProjects() : {

}
