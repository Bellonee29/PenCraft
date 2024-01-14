package com.penCraft.data.entity

import com.penCraft.data.database.table.Blogs
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EntityBlog(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EntityBlog>(Blogs)

    var user by EntityUser referencedOn Blogs.user
    var username by Blogs.username
    var title by Blogs.title
    var description by Blogs.description
    var created by Blogs.created
    var updated by Blogs.updated
}