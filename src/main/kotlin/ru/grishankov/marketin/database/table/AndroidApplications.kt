package ru.grishankov.marketin.database.table

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import ru.grishankov.marketin.PATH_SERVER_FILES
import ru.grishankov.marketin.models.App

object AppTable : Table<AppTableEntity>("applications") {
    val id = int("id").primaryKey().bindTo { it.id }
    val label = varchar("label").bindTo { it.label }
    val desc = varchar("desc").bindTo { it.desc }
    val tag = varchar("tag").bindTo { it.tag }
    val packageName = varchar("package_name").bindTo { it.packageName }
}

interface AppTableEntity : Entity<AppTableEntity> {
    companion object : Entity.Factory<AppTableEntity>()

    val id: Int
    val label: String
    val desc: String
    val tag: String
    val packageName: String
}

fun AppTableEntity.toApp(): App {
    return App(id, label, desc, tag.split(", "), "$PATH_SERVER_FILES/$id.png", packageName)
}

val Database.apps
    get() = this.sequenceOf(AppTable)

