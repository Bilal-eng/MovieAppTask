package com.teknasyon.movieapptask.utils

import com.teknasyon.movieapptask.MovieApplication
import com.teknasyon.movieapptask.connection.RestControllerFactory
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException


object ErrorUtils {
    fun parseError(response: retrofit2.Response<*>): APIError? {
        val converter: Converter<ResponseBody, APIError>? =
            RestControllerFactory(MovieApplication.appContext).retrofit
                ?.responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))
        val error: APIError?
        error = try {
            converter?.convert(response.errorBody())
        } catch (e: IOException) {
            return APIError()
        }
        return error
    }
}