package com.example.data.repository

import com.example.data.network.ApiService
import com.example.domain.models.UserData
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository{

    override fun getUsers(): Flow<List<UserData>> {
        return apiService.getUsers()
    }

}