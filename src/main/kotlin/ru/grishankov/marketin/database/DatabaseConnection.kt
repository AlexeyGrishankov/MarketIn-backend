package ru.grishankov.marketin.database

import org.ktorm.database.Database
import ru.grishankov.marketin.BD_DRIVER
import ru.grishankov.marketin.BD_PASSWORD
import ru.grishankov.marketin.BD_URL
import ru.grishankov.marketin.BD_USER

object DatabaseConnection {
    val database = Database.connect(
        url = BD_URL,
        driver = BD_DRIVER,
        user = BD_USER,
        password = BD_PASSWORD,
    )
}