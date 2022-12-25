package com.example.salttest.data.di

import com.example.salttest.data.repository.Repository
import com.example.salttest.data.use_case.DataUseCase
import com.example.salttest.data.use_case.GetLogin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideUseCases(
        repository: Repository
    ): DataUseCase {
        return DataUseCase(
            getLogin = GetLogin(repository)
        )
    }
}