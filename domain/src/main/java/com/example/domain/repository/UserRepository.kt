package com.example.domain.repository

import com.example.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers() : Flow<List<UserData>>
}