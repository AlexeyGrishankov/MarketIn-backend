package ru.grishankov.marketin.database.table

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import ru.grishankov.marketin.PATH_SERVER_FILES
import ru.grishankov.marketin.models.Market

object MarketTable : Table<MarketEntity>("market") {
    val id = int("id").primaryKey().bindTo { it.id }
    val version = varchar("version").bindTo { it.version }
    val url = varchar("url").bindTo { it.url }
}

interface MarketEntity: Entity<MarketEntity> {
    companion object : Entity.Factory<MarketEntity>()

    val id: Int
    val version: String
    val url: String
}

fun MarketEntity.toMarket(): Market {
    return Market(id, version, "$PATH_SERVER_FILES/$url")
}

val Database.markets
    get() = this.sequenceOf(MarketTable)