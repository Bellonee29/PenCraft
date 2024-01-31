@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.penCraft.data.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import com.penCraft.data.database.table.Blogs
import com.penCraft.data.database.table.Tokens
import com.penCraft.data.database.table.Users
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.newFixedThreadPoolContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import java.net.URI
import kotlin.coroutines.CoroutineContext

@OptIn(DelicateCoroutinesApi::class)
class DatabaseProvider : DatabaseProviderContract, KoinComponent {

    private val dispatcher: CoroutineContext

    init {
        dispatcher = newFixedThreadPoolContext(5, "database-pool")
    }

    override fun init() {
        Database.connect(hikari())
        transaction {
            create(Users)
            create(Blogs)
            create(Tokens)
        }
    }

/*    private fun hikari(): HikariDataSource {
        HikariConfig().run {
            driverClassName = driverClass
            jdbcUrl = "jdbc:postgresql://${host}:${port}/${dbname}"
            username = user
            password = dbpassword
            isAutoCommit = false
            maximumPoolSize = 5
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
            return HikariDataSource(this)
        }
    }

    companion object DatabaseConfig {
        const val driverClass = "org.postgresql.Driver"
        const val host = "localhost"
        const val port = 3500
        const val dbname = "blogfy"
        const val user = "postgres"
        const val dbpassword = "root"
    }*/
}

interface DatabaseProviderContract {
    fun init()
}