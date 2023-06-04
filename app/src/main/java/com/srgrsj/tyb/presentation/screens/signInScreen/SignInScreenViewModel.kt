package com.srgrsj.tyb.presentation.screens.signInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgrsj.tyb.data.user.AccountData
import com.srgrsj.tyb.util.Resource
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.srgrsj.tyb.domain.user.usecases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        userUseCase.userSignInUseCase(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Success"))
                }

                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }

                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message))
                }
            }

        }
        AccountData.ID = Firebase.auth.currentUser?.uid
        AccountData.EMAIL = Firebase.auth.currentUser?.email

    }
}