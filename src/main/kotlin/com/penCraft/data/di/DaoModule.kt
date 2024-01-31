package com.penCraft.data.di

import com.penCraft.data.dao.BlogsDao
import com.penCraft.data.dao.TokenDao
import com.penCraft.data.dao.UserDao
import com.penCraft.data.database.DatabaseProvider
import com.penCraft.data.database.DatabaseProviderContract
import com.penCraft.data.database.table.Blogs
import com.penCraft.data.database.table.Tokens
import com.penCraft.data.database.table.Users
import org.koin.dsl.module

object DaoModule {
    val koinBeans = module {
        single<TokenDao> { Tokens }
        single<UserDao> { Users }
        single<BlogsDao> { Blogs }
        single<DatabaseProviderContract> { DatabaseProvider() }
    }
}