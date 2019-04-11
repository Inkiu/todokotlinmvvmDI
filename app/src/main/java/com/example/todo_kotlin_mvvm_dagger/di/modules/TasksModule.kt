package com.example.todo_kotlin_mvvm_dagger.di.modules

import android.content.Context
import com.example.todo_kotlin_mvvm_dagger.clean.data.HelloRepository
import com.example.todo_kotlin_mvvm_dagger.clean.domain.HelloUseCase
import dagger.Module
import dagger.Provides

@Module
abstract class TasksModule {

    @Module
    companion object {
        @JvmStatic @Provides fun provideHelloUseCase(helloRepository: HelloRepository): HelloUseCase {
            return HelloUseCase(helloRepository)
        }
    }
}