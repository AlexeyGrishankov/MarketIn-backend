package ru.grishankov.marketin.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.entity.map
import ru.grishankov.marketin.database.table.apps
import ru.grishankov.marketin.database.table.toApp
import ru.grishankov.marketin.models.AppShortData
import ru.grishankov.marketin.models.AppShortDataList

fun Application.listAppRoute(db: Database) {
    routing {
        get("/api/applications") {

            val apps = db.apps
                .map { it.toApp() }
                .map { AppShortData(it.id, it.label, it.tags, it.logoUrl) }
                .let { AppShortDataList(it) }

            call.respond(
                status = HttpStatusCode.OK,
                message = apps
            )
        }
    }
}