package com.example.todo_kotlin_mvvm_dagger.di.modules

import dagger.Module
import dagger.Provides

@Module
object TestModule {
    @Provides
    @JvmStatic
    fun provideTestString(): String = "dagger test successful!"
}