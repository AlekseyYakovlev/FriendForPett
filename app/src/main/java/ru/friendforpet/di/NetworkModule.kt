package ru.friendforpet.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.friendforpet.BuildConfig
import ru.friendforpet.data.remote.RestService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideRestService(
        retrofit: Retrofit
    ): RestService =
        retrofit.create(RestService::class.java)

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,

        ): OkHttpClient =
        OkHttpClient().newBuilder()
            .readTimeout(2, TimeUnit.SECONDS)  // socket timeout (GET)
            .writeTimeout(5, TimeUnit.SECONDS) // socket timeout (POST, PUT, etc.)
            .addInterceptor(httpLoggingInterceptor)    // log requests/results
            .build()

    @Provides
    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
}