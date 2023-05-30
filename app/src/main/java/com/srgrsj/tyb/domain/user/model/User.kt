package com.srgrsj.tyb.domain.user.model

import java.util.UUID

data class User(
    val email: String,
    val id: String = UUID.randomUUID().toString()
//    val avatar:

)