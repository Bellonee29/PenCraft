package com.penCraft.app

import com.auth0.jwt.interfaces.JWTVerifier
import com.penCraft.app.firebase.FirebaseAdmin
import com.penCraft.data.dao.UserDao
import com.penCraft.data.database.DatabaseProviderContract
import dev.zlagi.application.plugins.configureKoin
import dev.zlagi.application.plugins.configureMonitoring
import dev.zlagi.application.plugins.configureSecurity
import dev.zlagi.application.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.annotation.KoinReflectAPI
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@OptIn(KoinReflectAPI::class)
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val databaseProvider by inject<DatabaseProviderContract>()
    val userDao by inject<UserDao>()
    val jwtVerifier by inject<JWTVerifier>()

    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
    val apiKey = environment.config.property("onesignal.apiKey").getString()

    configureKoin()
    configureMonitoring()
    configureSerialization()
    configureSecurity(userDao, jwtVerifier)
    configureRouting(client, apiKey)

    // initialize database
    databaseProvider.init()

    // initialize Firebase Admin SDK
    FirebaseAdmin.init()
}

