package com.hassan.saryassessment.core.data.entity

data class ApiResponse<T>(
    val status: Boolean,
    val result: T,
    val message: String? = null
)