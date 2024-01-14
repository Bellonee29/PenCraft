package com.penCraft.data.model

import com.penCraft.data.entity.EntityToken

data class Token(
    val id: String,
    val token: String,
    val expirationTime: String
) {
    companion object {
        fun fromEntity(entity: EntityToken) =
            Token(
                entity.id.value.toString(),
                entity.token,
                entity.expirationTime
            )
    }
}