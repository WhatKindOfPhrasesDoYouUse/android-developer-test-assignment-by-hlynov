package com.example.khlynovapp.data.api.response;

import com.example.khlynovapp.data.api.response.error.ApiError;

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val apiError: ApiError) : ApiResult<Nothing>()
}
