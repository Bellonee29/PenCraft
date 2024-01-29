package com.penCraft.app.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RevokeTokenRequest(
    val token: String
)