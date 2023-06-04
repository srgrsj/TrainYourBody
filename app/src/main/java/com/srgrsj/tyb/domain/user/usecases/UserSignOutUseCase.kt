package com.srgrsj.tyb.domain.user.usecases

import com.srgrsj.tyb.domain.user.repository.UserRepository

class UserSignOutUseCase(private val repository: UserRepository) {
    operator fun invoke() {
        repository.singOutUser()
    }
}