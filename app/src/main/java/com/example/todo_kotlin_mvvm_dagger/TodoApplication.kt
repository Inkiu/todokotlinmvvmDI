package com.example.todo_kotlin_mvvm_dagger

import android.app.Application
import com.example.todo_kotlin_mvvm_dagger.di.appModules
import com.example.todo_kotlin_mvvm_dagger.di.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TodoApplication)
            modules(appModules + viewModules)
        }
    }
}