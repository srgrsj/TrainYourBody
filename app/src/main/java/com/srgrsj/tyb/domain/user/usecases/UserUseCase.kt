package com.srgrsj.tyb.domain.user.usecases

data class UserUseCase(
    val addUserUseCase: AddUserUseCase,
    val userSignInUseCase: UserSignInUseCase,
    val userSignUpUseCase: UserSignUpUseCase,
    val userSignOutUseCase: UserSignOutUseCase
)