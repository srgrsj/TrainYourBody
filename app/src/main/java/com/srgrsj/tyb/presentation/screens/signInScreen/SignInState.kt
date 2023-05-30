package com.srgrsj.tyb.presentation.screens.signInScreen

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
