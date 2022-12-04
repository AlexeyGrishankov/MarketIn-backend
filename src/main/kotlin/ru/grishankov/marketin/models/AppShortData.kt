package ru.grishankov.marketin.models

import kotlinx.serialization.Serializable

@Serializable
data class AppShortData(
    val id: Int,
    val label: String,
    val tags: List<String>,
    val logoUrl: String,
)

@Serializable
data class AppShortDataList(
    val data: List<AppShortData>
)
