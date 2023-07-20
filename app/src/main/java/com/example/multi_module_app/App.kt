package com.example.multi_module_app

import android.app.Application
import com.example.multi_module_app.di.components.ApplicationComponent
import com.example.multi_module_app.di.components.DaggerApplicationComponent

class App : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .factory()
            .create(this)

        applicationComponent.inject(this)
    }
}