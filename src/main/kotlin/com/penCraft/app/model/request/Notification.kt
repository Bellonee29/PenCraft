package com.penCraft.app.model.request

import com.penCraft.app.model.request.NotificationMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    // @SerialName("include_external_user_ids")
    // val includeExternalUserIds: List<String>,
    @SerialName("included_segments")
    val includedSegments: List<String>,
    val contents: NotificationMessage,
    val headings: NotificationMessage,
    @SerialName("app_id")
    val appId: String,
)