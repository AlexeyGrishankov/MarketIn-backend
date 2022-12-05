package ru.grishankov.marketin

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import ru.grishankov.marketin.database.DatabaseConnection
import ru.grishankov.marketin.routes.appData
import ru.grishankov.marketin.routes.download
import ru.grishankov.marketin.routes.listAppRoute
import ru.grishankov.marketin.routes.updateApp
import java.io.File

fun main() {
    initConfig()
    embeddedServer(Netty, port = PORT, host = HOST) {
        install(ContentNegotiation) { json() }
        install(CORS) {
            anyHost()
            HttpMethod.DefaultMethods.forEach(::allowMethod)
            allowHeader(HttpHeaders.AccessControlAllowOrigin)
            allowHeader(HttpHeaders.ContentType)
            allowHeader(HttpHeaders.Authorization)
        }
        routes()
    }.start(wait = true)
}

fun Application.routes() {
    with(DatabaseConnection.database) {
        listAppRoute(this)
        appData(this)
        updateApp(this)
        download()
    }
}

fun initConfig() {
    val cfgFolder = File(CFG_FOLDER_PATH)
    val cfgFile = File(cfgFolder, CFG_FILE_PATH)

    if (cfgFolder.mkdir()) {
        cfgFile.outputStream().buffered().use {
            configProps.setProperty("host_server", "")
            configProps.setProperty("port_server", "")

            configProps.setProperty("bd_url", "")
            configProps.setProperty("bd_driver", "")
            configProps.setProperty("bd_user", "")
            configProps.setProperty("bd_password", "")

            configProps.store(it, "main config")
        }
    } else {
        cfgFile.inputStream().buffered().use {
            configProps.load(it)

            HOST = configProps.getProperty("host_server")
            PORT = configProps.getProperty("port_server").toInt()

            BD_URL = configProps.getProperty("bd_url")
            BD_DRIVER = configProps.getProperty("bd_driver")
            BD_USER = configProps.getProperty("bd_user")
            BD_PASSWORD = configProps.getProperty("bd_password")
        }
    }
}