package com.srgrsj.tyb.util

sealed class DomainResult<out T> {
    data class Success<out T>(val data: T) : DomainResult<T>()
    data class Error(val exception: Exception) : DomainResult<Nothing>()
    object Loading : DomainResult<Nothing>()
}
