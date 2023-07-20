package com.example.multi_module_app.di.modules

import com.example.data.network.ApiService
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [DataModule.BindModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Module
    abstract class BindModule {
        @Binds
        abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository
    }
}