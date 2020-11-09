package com.pupptmstr.puppetmakebackend.controllers

import com.pupptmstr.puppetmakebackend.dbrepo.NewsRepo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.newsController(newsRepo: NewsRepo) {

    route("/news") {

        get("/all") {
            call.respond(newsRepo.getAll())
        }

        get("/one") {
            val id = call.request.queryParameters["id"]?.toLong()
            if (id != null)
                call.respond(newsRepo.getById(id))
            else
                call.respond(HttpStatusCode.BadRequest)
        }
    }

}