package com.hassan.saryassessment.core.presentation.errors

import java.lang.Exception

class CustomException(
    private val errorMessage: String? = null,
    private val errorResource: Int? = null
): Exception() {

    fun getErrorMessage() = errorMessage

    fun getErrorResource() = errorResource
}