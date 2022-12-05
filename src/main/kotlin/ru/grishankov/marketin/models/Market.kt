package ru.grishankov.marketin.models

import kotlinx.serialization.Serializable

@Serializable
data class MarketUpdate(
    val lastVersion: String,
    val urlDownload: String,
    val isNeedUpdate: Boolean,
)

@Serializable
data class MarketUpdateReq(
    val currentVersion: String,
)

@Serializable
data class Market(
    val id: Int,
    val version: String,
    val url: String,
)
