package com.srgrsj.tyb.domain.user.repository

import com.google.firebase.auth.AuthResult
import com.srgrsj.tyb.domain.user.model.User
import com.srgrsj.tyb.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun singOutUser()
}