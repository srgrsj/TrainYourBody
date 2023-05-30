package com.srgrsj.tyb.presentation.screens.accountScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgrsj.tyb.data.firebase.auth.AccountData
import com.srgrsj.tyb.domain.auth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject  constructor(
    private val repository: AuthRepository
): ViewModel() {
    fun singOutUser() = viewModelScope.launch {
        repository.singOutUser()

        AccountData.EMAIL = ""
        AccountData.ID = ""
    }
}