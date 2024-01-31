package com.penCraft.app.auth.principal

import com.penCraft.data.model.User
import io.ktor.auth.*

class UserPrincipal(val user: User) : Principal