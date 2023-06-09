package com.srgrsj.tyb.domain.user.usecases

import com.google.firebase.auth.AuthResult
import com.srgrsj.tyb.domain.user.repository.UserRepository
import com.srgrsj.tyb.util.Resource
import kotlinx.coroutines.flow.Flow

class UserSignUpUseCase(private val repository: UserRepository) {
    operator fun invoke(email: String, password: String): Flow<Resource<AuthResult>> {
        return repository.registerUser(email, password)
    }
}