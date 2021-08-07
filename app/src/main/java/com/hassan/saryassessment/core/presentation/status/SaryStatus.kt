package com.hassan.saryassessment.core.presentation.status

import com.hassan.saryassessment.core.presentation.errors.CustomException

sealed class SaryStatus<T> {
    class SuccessStatus<Type>(val data: Type): SaryStatus<Type>()
    class ErrorStatus<Type>(val error: CustomException): SaryStatus<Type>()
    class LoadingStatus<Type>(val boolean: Boolean): SaryStatus<Type>()
}