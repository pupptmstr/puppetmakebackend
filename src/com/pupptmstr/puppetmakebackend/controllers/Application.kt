package com.pupptmstr.puppetmakebackend.controllers

import com.pupptmstr.puppetmakebackend.dbrepo.NewsRepo
import com.pupptmstr.puppetmakebackend.dbrepo.ProjectsRepo
import com.pupptmstr.puppetmakebackend.dbrepo.TeammatesRepo
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.gson.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost()
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(ContentNegotiation) {
        gson {
        }
    }

    val newsRepo = NewsRepo()
    val projectsRepo = ProjectsRepo()
    val teammatesRepo = TeammatesRepo()

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/news") {
            call.respond(newsRepo.getAll())
        }

        get("/projects") {
            call.respond(projectsRepo.getAll())
        }

        get("/teammate") {
            call.respond(teammatesRepo.getAll())
        }

        get("/news/one") {
            val id = call.request.queryParameters["id"]?.toLong()
            if (id != null)
                call.respond(newsRepo.getById(id))
            else
                call.respond(HttpStatusCode.BadRequest)
        }

        get("/project/one") {
            val id = call.request.queryParameters["id"]?.toLong()
            if (id != null)
                call.respond(projectsRepo.getById(id))
            else
                call.respond(HttpStatusCode.BadRequest)
        }

        get("/teammate/one") {
            val id = call.request.queryParameters["id"]?.toLong()
            if (id != null)
                call.respond(teammatesRepo.getById(id))
            else
                call.respond(HttpStatusCode.BadRequest)
        }
    }
}

