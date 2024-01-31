package com.penCraft.app.auth.firebase

import com.penCraft.app.auth.firebase.FirebaseAuthenticationProvider
import kotlinx.coroutines.runBlocking

object FirebaseConfig {
    fun FirebaseAuthenticationProvider.Configuration.configure() {
        principal = { email ->
            //this is where you'd make a db call to fetch your User profile
            runBlocking { FirebaseUserPrincipal(email) }
        }
    }
}