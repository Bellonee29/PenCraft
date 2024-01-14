package com.penCraft.data.model

import com.penCraft.data.database.table.Blogs
import com.penCraft.data.entity.EntityBlog
import com.penCraft.data.entity.EntityUser

data class BlogDataModel(
    val id: Int,
    val username: String,
    val title: String,
    val description: String,
    val created: String,
    val updated: String?
) {
    companion object {
        fun fromEntity(entity: EntityBlog) =
            BlogDataModel(
                entity.id.value,
                entity.username,
                entity.title,
                entity.description,
                entity.created,
                entity.updated)
    }
}




