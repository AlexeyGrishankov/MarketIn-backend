package ru.grishankov.marketin.routes

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.download() {
    routing {
        static("/files") {
            files("resources/img")
            files("resources/apk")
        }
    }
}