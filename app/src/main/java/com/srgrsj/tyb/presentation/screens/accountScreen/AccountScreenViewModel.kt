package com.srgrsj.tyb.presentation.screens.accountScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgrsj.tyb.data.user.AccountData
import com.srgrsj.tyb.domain.user.usecases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject constructor(
    private val userUseCase: UserUseCase,

    ) : ViewModel() {
    fun singOutUser() = viewModelScope.launch {
        userUseCase.userSignOutUseCase()

        AccountData.EMAIL = ""
        AccountData.ID = ""
    }
}