package com.penCraft.app.auth.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RevokeTokenRequest(
    val token: String
)