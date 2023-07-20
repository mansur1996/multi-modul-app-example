package com.example.multi_module_app.utils

import com.example.domain.models.UserData

sealed class UserResource {
    object Loading : UserResource()
    data class Success(val list: List<UserData>?) : UserResource()
    data class Error(val message: String?) : UserResource()
}
