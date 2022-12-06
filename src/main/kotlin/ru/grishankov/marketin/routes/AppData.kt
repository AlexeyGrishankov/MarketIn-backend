package ru.grishankov.marketin.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.map
import org.ktorm.entity.sortedBy
import ru.grishankov.marketin.database.table.apps
import ru.grishankov.marketin.database.table.toApp
import ru.grishankov.marketin.database.table.toVersion
import ru.grishankov.marketin.database.table.versions
import ru.grishankov.marketin.models.AppData

fun Application.appData(db: Database) {
    routing {
        get("api/applications/{id}") {

            val appId = call.parameters["id"]?.toInt()

            if (appId == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Application ID not found"
                )
            }

            val app = db.apps.find { it.id eq appId!! }?.toApp()

            if (app == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Application with ID = $appId not found"
                )
            }

            val versions = db.versions.filter { it.idApp eq appId!! }.sortedBy { it.id }.map { it.toVersion() }

            val appData = AppData(
                id = app!!.id,
                label = app.label,
                desc = app.desc,
                tags = app.tags,
                logoUrl = app.logoUrl,
                packageName = app.packageName,
                lastVersion = versions.lastOrNull(),
                versions = versions
            )

            call.respond(
                status = HttpStatusCode.OK,
                message = appData
            )
        }
    }
}