package com.srgrsj.tyb.domain.user.repository

import com.srgrsj.tyb.domain.user.model.User

interface UserRepository {
    suspend fun insertUser(user: User)
}