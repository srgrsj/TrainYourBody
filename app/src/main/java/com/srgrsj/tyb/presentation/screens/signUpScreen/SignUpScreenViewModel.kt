package com.srgrsj.tyb.presentation.screens.signUpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgrsj.tyb.domain.user.model.User
import com.srgrsj.tyb.domain.user.usecases.UserUseCase
import com.srgrsj.tyb.data.firebase.auth.AccountData
import com.srgrsj.tyb.domain.auth.repository.AuthRepository
import com.srgrsj.tyb.util.Resource
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun registerUser(name: String, email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signUpState.send(SignUpState(isSuccess = "Sign Up Success"))
                }

                is Resource.Loading -> {
                    _signUpState.send(SignUpState(isLoading = true))
                }

                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = result.message))
                }
            }

        }

        AccountData.ID = Firebase.auth.currentUser?.uid
        AccountData.EMAIL = Firebase.auth.currentUser?.email


        userUseCase.addUserUseCase.invoke(
            User(
                id = AccountData.ID.toString(),
                email = AccountData.EMAIL.toString()
            )
        )
    }
}