package ru.grishankov.marketin.models

import kotlinx.serialization.Serializable

@Serializable
data class AppVersion(
    val id: Int,
    val idApp: Int,
    val title: String,
    val versionCode: Int,
    val description: String,
    val url: String,
)