package ru.grishankov.marketin.models

import kotlinx.serialization.Serializable

@Serializable
data class App(
    val id: Int,
    val label: String,
    val desc: String,
    val tags: List<String>,
    val logoUrl: String,
    val packageName: String,
)