package dev.zlagi.application.plugins

import com.auth0.jwt.interfaces.JWTVerifier
import com.penCraft.app.auth.JWTController
import com.penCraft.app.auth.PasswordEncryptor
import com.penCraft.app.auth.PasswordEncryptorContract
import com.penCraft.app.auth.TokenProvider
import com.penCraft.app.auth.controller.di.ControllerModule
import dev.zlagi.data.di.DaoModule
import io.ktor.application.*
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger

@KoinReflectAPI
fun Application.configureKoin() {

    install(feature = Koin) {
        slf4jLogger(level = org.koin.core.logger.Level.ERROR) //This params are the workaround itself
        modules(
            module {
                single<JWTVerifier> { JWTController.verifier }
                single<TokenProvider> { JWTController }
                single<PasswordEncryptorContract> { PasswordEncryptor }
            },
            DaoModule.koinBeans,
            ControllerModule.koinBeans
        )
    }
}
