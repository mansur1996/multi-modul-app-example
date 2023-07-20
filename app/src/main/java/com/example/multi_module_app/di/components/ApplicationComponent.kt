package com.example.multi_module_app.di.components

import com.example.multi_module_app.App
import com.example.multi_module_app.presentation.MainActivity
import com.example.multi_module_app.di.modules.ApplicationModule
import com.example.multi_module_app.di.modules.DataModule
import com.example.multi_module_app.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app : App) : ApplicationComponent
    }

    fun inject(app: App)
    fun inject(mainActivity: MainActivity)

}