package com.example.todo_kotlin_mvvm_dagger.di

import android.app.Application
import com.example.todo_kotlin_mvvm_dagger.TodoApplication
import com.example.todo_kotlin_mvvm_dagger.data.di.DataModule
import com.example.todo_kotlin_mvvm_dagger.ui.tasks.TasksModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class,
        DataModule::class,
        TasksModule::class
    ]
)
interface AppComponent : AndroidInjector<TodoApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}