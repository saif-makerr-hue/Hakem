package com.example.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.data.datasource.api.PatientService


@Module
@InstallIn(
    SingletonComponent::class
)
object NetworkModule  {
    private const val BASE_URL: String = "https://68d9665e90a75154f0da4e1f.mockapi.io/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//Use @Bind
    @Singleton
    @Provides
    fun providePatientService(retrofit: Retrofit): PatientService {
        return retrofit.create(PatientService::class.java)
    }
}

