package com.penCraft.data.dao

import com.penCraft.data.model.Token

interface TokenDao {
    suspend fun store(
        userId: Int, token: String, expirationTime: String
    ): String
    suspend fun getAllById(userId: Int): List<Token>
    suspend fun exists(userId: Int, token: String): Boolean
    suspend fun deleteById(tokenId: String): Boolean
}