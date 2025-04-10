package com.example.comprasapp.di

import com.example.comprasapp.data.repository.FakeStoreRepositoryImpl
import com.example.comprasapp.data.repository.PlatziRepositoryImpl
import com.example.comprasapp.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    @Named("Fake")
    abstract fun bindFakeStoreRepository(
        fakeStoreRepositoryImpl: FakeStoreRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    @Named("Platzi")
    abstract fun bindPlatziRepository(
        platziRepositoryImpl: PlatziRepositoryImpl
    ): ProductRepository
}