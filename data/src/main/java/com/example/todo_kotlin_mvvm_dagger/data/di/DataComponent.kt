package com.example.todo_kotlin_mvvm_dagger.data.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ContextModule::class,
        TaskRepositoryModule::class
    ]
)
interface DataComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): DataComponent.Builder

        fun build(): DataComponent
    }
}
