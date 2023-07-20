package com.example.multi_module_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.UserUseCase
import com.example.multi_module_app.utils.UserResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    fun getUsersFlow(): StateFlow<UserResource> {
        val flow = MutableStateFlow<UserResource>(UserResource.Loading)
        viewModelScope.launch {
            userUseCase.getUsers()
                .catch {
                    flow.emit(UserResource.Error(it.message))
                }.collect {
                    if (it.isSuccess) {
                        flow.emit(UserResource.Success(it.getOrNull()))
                    } else if (it.isFailure) {
                        flow.emit(UserResource.Error(it.exceptionOrNull()?.message))
                    }
                }
        }
        return flow
    }


}