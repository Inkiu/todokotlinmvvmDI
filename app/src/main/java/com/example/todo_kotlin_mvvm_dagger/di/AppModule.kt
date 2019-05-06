package com.example.todo_kotlin_mvvm_dagger.di

import android.app.Application
import android.content.Context
import com.example.todo_kotlin_mvvm_dagger.data.ApplicationContext
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    @ApplicationContext
    abstract fun bindContext(application: Application): Context
}