package com.example.comprasapp.di

import com.example.comprasapp.data.remote.api.FakeStoreApi
import com.example.comprasapp.data.remote.api.PlatziApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit para FakeStore (País A)
    @Provides
    @Singleton
    @Named("fakeStoreRetrofit")
    fun provideFakeStoreRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideFakeStoreApi(@Named("fakeStoreRetrofit") retrofit: Retrofit): FakeStoreApi {
        return retrofit.create(FakeStoreApi::class.java)
    }

    // Retrofit para Platzi (País B)
    @Provides
    @Singleton
    @Named("platziRetrofit")
    fun providePlatziRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")  // Asegúrate de que la URL sea la correcta
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun providePlatziApi(@Named("platziRetrofit") retrofit: Retrofit): PlatziApi {
        return retrofit.create(PlatziApi::class.java)
    }
}