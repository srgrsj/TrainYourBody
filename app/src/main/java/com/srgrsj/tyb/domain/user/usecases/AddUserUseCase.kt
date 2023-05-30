package com.srgrsj.tyb.domain.user.usecases

import com.srgrsj.tyb.domain.user.model.User
import com.srgrsj.tyb.domain.user.repository.UserRepository


class AddUserUseCase(private val repository: UserRepository) {
    suspend fun invoke(user: User) {
        repository.insertUser(user = user)
    }
}