package com.mrwhoknwos.weatherapp.di

import android.content.Context
import com.mrwhoknwos.weatherapp.MainApplication
import com.mrwhoknwos.weatherapp.network.ApiService
import com.mrwhoknwos.weatherapp.util.Constants
import com.mrwhoknwos.weatherapp.util.Secret
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(
        @ApplicationContext context: Context
    ): MainApplication = context as MainApplication


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

    @Singleton
    @Provides
    fun provideNetworkService(
        @Named("BASE_URL") baseUrl: String,
        converterFactory: MoshiConverterFactory,
        client: OkHttpClient
    ): ApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(client)
        .build().create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Singleton
    @Provides
    @Named("API_KEY")
    fun provideBaseApiKey(): String = Secret.API_KEY
}