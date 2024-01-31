package com.penCraft.app.controller.di

import com.penCraft.app.controller.auth.AuthController
import com.penCraft.app.controller.auth.DefaultAuthController
import com.penCraft.app.controller.blog.BlogController
import org.koin.dsl.module

object ControllerModule {
    val koinBeans = module {
        single<AuthController> { DefaultAuthController() }
        single<BlogController> { DefaultBlogController() }
    }
}