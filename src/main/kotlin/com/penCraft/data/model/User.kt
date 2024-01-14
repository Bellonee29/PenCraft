package com.penCraft.data.model

import com.penCraft.data.entity.EntityUser

data class User(
    val id: Int,
    val email: String,
    val username: String,
    val password: String?
) {
    companion object {
        fun fromEntity(entity: EntityUser) = User(
            entity.id.value,
            entity.email,
            entity.username,
            entity.password)
    }
}