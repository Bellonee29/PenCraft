package dev.zlagi.data.di

import com.penCraft.data.dao.BlogsDao
import com.penCraft.data.dao.TokenDao
import com.penCraft.data.dao.UserDao
import dev.zlagi.data.database.DatabaseProvider
import dev.zlagi.data.database.DatabaseProviderContract
import dev.zlagi.data.database.table.Blogs
import com.penCraft.data.database.table.Tokens
import dev.zlagi.data.database.table.Users
import org.koin.dsl.module

object DaoModule {
    val koinBeans = module {
        single<TokenDao> { Tokens }
        single<UserDao> { Users }
        single<BlogsDao> { Blogs }
        single<DatabaseProviderContract> { DatabaseProvider() }
    }
}