package com.bahaa.task.utils

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: String) : Response<Nothing>()
    data class Loading(var isLoading: Boolean = true) : Response<Nothing>()
}
