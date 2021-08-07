package com.hassan.saryassessment.core.presentation.errors

import android.app.Activity
import android.widget.Toast
import com.hassan.saryassessment.R
import com.hassan.saryassessment.core.data.entity.ApiResponse
import org.apache.http.conn.ConnectTimeoutException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Throwable.asCustomException(): CustomException{
    return if(this is HttpException){
        when(code()){
            500, 502 -> CustomException(errorResource = R.string.server_error)
            408 -> CustomException(errorResource = R.string.timeout_error)
            401 -> CustomException(errorResource = R.string.unauthorized_error)
            else -> CustomException(errorResource = R.string.unknown_error)
        }
    } else if(this is TimeoutException || this is SocketTimeoutException || this is ConnectTimeoutException)
        CustomException(errorResource = R.string.timeout_error)
    else if(this is ConnectException)
        CustomException(errorResource = R.string.server_error)
    else if(this is UnknownHostException)
        CustomException(errorResource = R.string.connection_error)
    else
        CustomException(errorResource = R.string.unknown_error)
}

fun <T>ApiResponse<T>.responseToCustomException(): CustomException{
    //This can be handled using status code
    // (But for now our Sary APIs there isn't status code) let's keep it simple
    return if (this.message == null)
        CustomException(errorResource = R.string.unknown_error)
    else CustomException(errorMessage = this.message)
}

fun Activity.handleCustomError(error: CustomException){
    val resourceId = error.getErrorResource()
    val msg: String? = if (resourceId != null)
        getString(resourceId)
    else if (error.getErrorMessage() != null)
        error.getErrorMessage()
    else getString(R.string.unknown_error)

    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}