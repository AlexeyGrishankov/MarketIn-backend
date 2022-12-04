package ru.grishankov.marketin.models

import kotlinx.serialization.Serializable

@Serializable
data class AppData(
    val id: Int,
    val label: String,
    val desc: String,
    val tags: List<String>,
    val logoUrl: String,
    val lastVersion: AppVersion?,
    val versions: List<AppVersion>,
    val packageName: String,
)