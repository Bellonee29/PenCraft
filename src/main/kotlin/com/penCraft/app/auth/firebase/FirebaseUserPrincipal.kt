package com.penCraft.app.auth.firebase

import io.ktor.auth.*

class FirebaseUserPrincipal(val email: String) : Principal