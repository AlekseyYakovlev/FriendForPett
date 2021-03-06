package ru.friendforpet.data.remote.interceptors

import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import ru.friendforpet.data.remote.err.ApiError
import ru.friendforpet.data.remote.err.ErrorBody
import javax.inject.Inject

class ErrorStatusInterceptor @Inject constructor(private val moshi: Moshi) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.isSuccessful) return response

        val errMessage = try {
            moshi
                .adapter(ErrorBody::class.java)
                .fromJson(response.body!!.string())?.message
        } catch (e: JsonEncodingException) {
            e.message
        }

        when (response.code) {
            400 -> throw ApiError.BadRequest(errMessage)
            401 -> throw ApiError.Unauthorized(errMessage)
            403 -> throw ApiError.Forbidden(errMessage)
            404 -> throw ApiError.NotFound(errMessage)
            500 -> throw ApiError.InternalServerError(errMessage)
            else -> throw ApiError.UnknownError(errMessage)
        }
    }
}