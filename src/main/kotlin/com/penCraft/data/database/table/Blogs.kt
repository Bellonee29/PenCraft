package com.penCraft.data.database.table

import com.penCraft.data.dao.BlogsDao
import com.penCraft.data.entity.EntityBlog
import com.penCraft.data.entity.EntityUser
import com.penCraft.data.model.BlogDataModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object Blogs : IntIdTable(), BlogsDao {
    val user = reference("user", Users)
    var username = varchar("username", length = 128)
    var title = varchar("title", length = 128)
    var description = text("description")
    var created = varchar("created", length = 48)
    var updated = varchar("updated", length = 48).nullable()

    override suspend fun store(
        userId: Int,
        username: String,
        title: String,
        description: String,
        created: String,
        updated: String?
    ): BlogDataModel =
        newSuspendedTransaction(Dispatchers.IO) {
            EntityBlog.new {
                this.user = EntityUser[userId]
                this.username = username
                this.title = title
                this.description = description
                this.created = created
                this.updated = updated
            }.let { BlogDataModel.fromEntity(it) }
        }

    override suspend fun searchByQuery(query: String): List<BlogDataModel> = newSuspendedTransaction(Dispatchers.IO) {
        EntityBlog.find {
            title regexp query or (description regexp query)
        }.sortedByDescending { it.id }
            .map { BlogDataModel.fromEntity(it) }
    }

    override suspend fun update(
        blogId: Int,
        title: String,
        description: String,
        updated: String?
    ): BlogDataModel =
        newSuspendedTransaction(Dispatchers.IO) {
            EntityBlog[blogId].apply {
                this.title = title
                this.description = description
                updated?.let {
                    this.updated = it
                }
            }.let { BlogDataModel.fromEntity(it) }
        }

    override suspend fun deleteById(blogId: Int): Boolean =
        newSuspendedTransaction(Dispatchers.IO) {
            val blog = EntityBlog.findById(blogId)
            blog?.run {
                delete()
                return@newSuspendedTransaction true
            }
            return@newSuspendedTransaction false
        }

    override suspend fun isBlogAuthor(blogId: Int, userId: Int): Boolean =
        newSuspendedTransaction(Dispatchers.IO) {
            EntityBlog.find {
                (Blogs.id eq blogId) and (user eq userId)
            }.firstOrNull() != null
        }

    override suspend fun exists(blogId: Int): Boolean = newSuspendedTransaction(Dispatchers.IO) {
        EntityBlog.findById(blogId) != null
    }
}