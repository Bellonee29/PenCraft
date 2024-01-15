package com.penCraft.app.auth.model.request

import kotlinx.serialization.Serializable

@Serializable
data class BlogRequest(
    val title: String,
    val description: String,
    val creationTime: String
)