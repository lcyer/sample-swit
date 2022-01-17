package com.lcyer.swit.result

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object None : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> {
                "Success[data=$data]"
            }
            is Error -> {
                "Error[exception=$exception]"
            }
            is Loading -> {
                "Loading"
            }
            is None -> {
                "None"
            }
        }
    }
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data