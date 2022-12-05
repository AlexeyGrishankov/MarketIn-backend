package ru.grishankov.marketin.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.entity.lastOrNull
import org.ktorm.entity.sortedBy
import ru.grishankov.marketin.database.table.markets
import ru.grishankov.marketin.database.table.toMarket
import ru.grishankov.marketin.models.MarketUpdate
import ru.grishankov.marketin.models.MarketUpdateReq

fun Application.updateApp(db: Database) {
    routing {
        post("api/market/update") {

            val currentVersion = call.receive<MarketUpdateReq>()

            val lastMarket = db.markets.sortedBy { it.id }.lastOrNull()?.toMarket()

            if (lastMarket == null) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = MarketUpdate("", "", false)
                )
            } else {
                val needUpdate = lastMarket.version > currentVersion.currentVersion

                val marketUpdate = MarketUpdate(lastMarket.version, lastMarket.url, needUpdate)

                call.respond(
                    status = HttpStatusCode.OK,
                    message = marketUpdate
                )
            }
        }
    }
}