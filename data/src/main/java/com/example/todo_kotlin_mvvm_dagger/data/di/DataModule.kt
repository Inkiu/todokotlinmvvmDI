package com.example.todo_kotlin_mvvm_dagger.data.di

import dagger.Module

@Module(
    includes = [
        TaskRepositoryModule::class
    ]
)
interface DataModule