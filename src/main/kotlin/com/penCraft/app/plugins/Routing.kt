package com.penCraft.app.plugins

import com.penCraft.app.router.authApi
import com.penCraft.app.router.blogApi
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.routing.*

fun Application.configureRouting(httpClient: HttpClient, apiKey: String) {
    routing {
        authApi()
        blogApi(httpClient, apiKey)
    }
}
