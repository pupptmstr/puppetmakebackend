package com.pupptmstr.puppetmakebackend.controllers

import com.pupptmstr.puppetmakebackend.dbrepo.TeammatesRepo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.teammatesController(teammatesRepo: TeammatesRepo) {

    route("teammates/") {

        get("/all") {
            call.respond(teammatesRepo.getAll())
        }

        get("/one") {
            val id = call.request.queryParameters["id"]?.toLong()
            if (id != null)
                call.respond(teammatesRepo.getById(id))
            else
                call.respond(HttpStatusCode.BadRequest)
        }

    }

}