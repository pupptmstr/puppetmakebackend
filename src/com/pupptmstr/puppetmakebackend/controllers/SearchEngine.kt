package com.pupptmstr.puppetmakebackend.controllers

import com.pupptmstr.puppetmakebackend.dbrepo.NewsRepo
import com.pupptmstr.puppetmakebackend.dbrepo.ProjectsRepo
import com.pupptmstr.puppetmakebackend.dbrepo.TeammatesRepo
import com.pupptmstr.puppetmakebackend.models.dbmodels.News
import com.pupptmstr.puppetmakebackend.models.dbmodels.Project
import com.pupptmstr.puppetmakebackend.models.responses.ResponseModel
import com.pupptmstr.puppetmakebackend.models.dbmodels.Teammate
import com.pupptmstr.puppetmakebackend.models.responses.SearchResponseModel
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.searchEngine(newsRepo: NewsRepo, teammatesRepo: TeammatesRepo, projectsRepo: ProjectsRepo) {
    route("/search") {
        get() {
            val query = call.request.queryParameters["query"]
            if (!query.isNullOrEmpty() && !query.isNullOrBlank()) {
                val news = searchNews(newsRepo, query)
                val teammates = searchTeammates(teammatesRepo, query)
                val projects = searchProjects(projectsRepo, query)
                call.respond(SearchResponseModel(news, teammates, projects))

            }
        }

    }
}

fun searchNews(newsRepo: NewsRepo, query: String): ResponseModel<News> = newsRepo.search(query)

fun searchTeammates(teammatesRepo: TeammatesRepo, query: String) : ResponseModel<Teammate> = teammatesRepo.search(query)


fun searchProjects(projectsRepo: ProjectsRepo, query: String) : ResponseModel<Project> = projectsRepo.search(query)
