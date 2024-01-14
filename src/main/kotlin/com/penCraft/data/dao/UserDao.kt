package com.penCraft.data.dao

import com.penCraft.data.model.User

interface UserDao {
    suspend fun storeUser(email: String, username: String, password: String?): User
    suspend fun findByID(userId: Int): User?
    suspend fun findByEmail(email: String): User?
    suspend fun isEmailAvailable(email: String): Boolean
    suspend fun updatePassword(userId: Int, password: String)
}
