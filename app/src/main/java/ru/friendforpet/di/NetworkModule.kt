package ru.friendforpet.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.friendforpet.BuildConfig
import ru.friendforpet.data.remote.RestService
import ru.friendforpet.data.remote.interceptors.ErrorStatusInterceptor
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

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        errorStatusInterceptor: ErrorStatusInterceptor
    ): OkHttpClient =
        OkHttpClient().newBuilder()
            .readTimeout(2, TimeUnit.SECONDS)  // socket timeout (GET)
            .writeTimeout(5, TimeUnit.SECONDS) // socket timeout (POST, PUT, etc.)
            .addInterceptor(httpLoggingInterceptor)    // log requests/results
            .addInterceptor(errorStatusInterceptor)    // intercept network errors
            .build()

    @Provides
    @Singleton
    fun provideErrorStatusInterceptor(
        moshi: Moshi
    ): ErrorStatusInterceptor =
        ErrorStatusInterceptor(moshi)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // for reflection
            .build()
}