package com.ajantha.starwarsplanets.presentation.util

import retrofit2.HttpException
import java.io.IOException

fun Throwable.toMessage(): String {
    return when (this) {
        is HttpException -> {
            when (this.code()) {
                in 400..499 -> "Input error. Please try again."
                in 500..599 -> "Server error. Please try again."
                else -> "Unknown error. Please try again."
            }
        }
        is IOException -> "Check your Internet connection."
        else -> this.message ?: "Unknown error. Please try again."
    }
}