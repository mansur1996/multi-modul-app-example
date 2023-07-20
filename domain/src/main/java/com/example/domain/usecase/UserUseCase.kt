package com.example.domain.usecase

import com.example.domain.models.UserData
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getUsers(): Flow<Result<List<UserData>>> {
        return userRepository.getUsers()
            .map {
                Result.success(it)
            }.catch {
                emit(Result.failure(it))
            }.flowOn(Dispatchers.IO)
    }

}