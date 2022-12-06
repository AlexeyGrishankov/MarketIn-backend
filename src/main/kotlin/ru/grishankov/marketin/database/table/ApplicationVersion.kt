package ru.grishankov.marketin.database.table

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import ru.grishankov.marketin.PATH_SERVER_FILES
import ru.grishankov.marketin.models.AppVersion
import java.io.File

object AppVersionTable : Table<AppVersionEntity>("versions") {
    val id = int("id").primaryKey().bindTo { it.id }
    val idApp = int("id_app").bindTo { it.idApp }
    val title = varchar("title").bindTo { it.title }
    val desc = varchar("desc").bindTo { it.desc }
    val url = varchar("url").bindTo { it.url }
    val versionCode = int("version_code").bindTo { it.versionCode }
    val date = varchar("date").bindTo { it.date }
}

interface AppVersionEntity : Entity<AppVersionEntity> {
    companion object : Entity.Factory<AppVersionEntity>()

    val id: Int
    val idApp: Int
    val title: String
    val desc: String?
    val url: String
    val versionCode: Int
    val date: String
}

fun AppVersionEntity.toVersion(): AppVersion {
    return AppVersion(id, idApp, title, versionCode,desc ?: "", "$PATH_SERVER_FILES/$url", date)
}

val Database.versions
    get() = this.sequenceOf(AppVersionTable)