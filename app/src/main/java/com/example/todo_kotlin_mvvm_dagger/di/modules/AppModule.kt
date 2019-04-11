package com.example.todo_kotlin_mvvm_dagger.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds abstract fun bindContext(application: Application): Context
}