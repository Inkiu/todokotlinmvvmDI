package com.example.todo_kotlin_mvvm_dagger.data.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}