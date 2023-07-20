package com.example.multi_module_app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.multi_module_app.App
import com.example.multi_module_app.R
import com.example.multi_module_app.presentation.viewmodel.UserViewModel
import com.example.multi_module_app.utils.UserResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope{

    @Inject
    lateinit var factory : ViewModelProvider.Factory

    private val viewModel: UserViewModel by viewModels {factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.applicationComponent.inject(this)

        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        launch {
            viewModel.getUsersFlow().collect{
                when(it){
                    is UserResource.Loading -> {
                        Log.d("TAG", "initViews: Loading")
                    }
                    is UserResource.Success -> {
                        Log.d("TAG", "initViews: ${it.list}")
                    }
                    is UserResource.Error -> {
                        Log.e("TAG", "initViews: ${it.message}")
                    }
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}