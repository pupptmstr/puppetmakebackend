package com.pupptmstr.puppetmakebackend.controllers

import com.google.gson.FieldNamingPolicy
import com.pupptmstr.puppetmakebackend.dbrepo.NewsRepo
import com.pupptmstr.puppetmakebackend.dbrepo.ProjectsRepo
import com.pupptmstr.puppetmakebackend.dbrepo.TeammatesRepo
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.event.Level
import java.io.File

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(CORS) {
        method(HttpMethod.Get)
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("CORS-CustomHeader")
        allowCredentials = true
        anyHost()
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            serializeNulls()
        }
    }

    val newsRepo = NewsRepo()
    val projectsRepo = ProjectsRepo()
    val teammatesRepo = TeammatesRepo()
    newsRepo.update()
    teammatesRepo.update()
    projectsRepo.update()


    routing {

        route("") {

            static("/static") {
                staticRootFolder = File("static")
                files(staticRootFolder!!.absolutePath)
                resources("static")
            }

        }

        route("/api/v1") {
            newsController(newsRepo)
            teammatesController(teammatesRepo)
            projectsController(projectsRepo)
            searchEngine(newsRepo, teammatesRepo, projectsRepo)
        }

        route("/update") {
            newsRepo.update()
            teammatesRepo.update()
            projectsRepo.update()
            get("") {
                call.respondText("DONE")
            }
         }
    }
}

