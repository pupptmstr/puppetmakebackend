package com.pupptmstr.puppetmakebackend.controllers

import com.pupptmstr.puppetmakebackend.dbrepo.ProjectsRepo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.projectsController(projectsRepo: ProjectsRepo) {

    route("projects/") {

        get("/all") {
            call.respond(projectsRepo.getAll())
        }

        get("/one") {
            val id = call.request.queryParameters["id"]?.toLong()
            if (id != null)
                call.respond(projectsRepo.getById(id))
            else
                call.respond(HttpStatusCode.BadRequest)
        }

    }

}