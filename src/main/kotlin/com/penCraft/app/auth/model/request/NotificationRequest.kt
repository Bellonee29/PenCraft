package com.penCraft.app.auth.model.request

import kotlinx.serialization.Serializable

@Serializable
data class NotificationRequest(
    val title: String
)