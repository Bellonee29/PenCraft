package com.penCraft.app.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequest(
    val email: String
)