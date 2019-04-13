package com.example.todo_kotlin_mvvm_dagger.di

import android.app.Application
import com.example.todo_kotlin_mvvm_dagger.TodoApplication
import com.example.todo_kotlin_mvvm_dagger.data.di.DataComponent
import com.example.todo_kotlin_mvvm_dagger.data.di.DataModule
import com.example.todo_kotlin_mvvm_dagger.data.di.TaskRepositoryModule
import com.example.todo_kotlin_mvvm_dagger.di.modules.ActivityBindingModule
import com.example.todo_kotlin_mvvm_dagger.di.modules.AppModule
import com.example.todo_kotlin_mvvm_dagger.di.modules.TasksModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
//    dependencies = [ // TODO - 이거 왜 안되는지 물어보기
//        DataComponent::class
//    ],
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

//        fun dataComponent(dataComponent: DataComponent): AppComponent.Builder

        fun build(): AppComponent
    }
}