package com.nyongnsikak.implementation.usecases

sealed class ResultUseCase<T> {
    data class Success<T>(val data: T) : ResultUseCase<T>()
    data class Error<T>(val errorEvent: Throwable) : ResultUseCase<T>()
    data class ErrorCode<T>(val code: Int? = null, val errorEvent: Throwable) : ResultUseCase<T>()
}
