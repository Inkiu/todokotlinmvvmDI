package com.example.todo_kotlin_mvvm_dagger

import com.example.todo_kotlin_mvvm_dagger.data.di.DaggerDataComponent
import com.example.todo_kotlin_mvvm_dagger.data.di.DataComponent
import com.example.todo_kotlin_mvvm_dagger.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TodoApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
//            .dataComponent(dataComponent())
            .application(this)
            .build()
    }

    private fun dataComponent(): DataComponent {
        return DaggerDataComponent.builder().application(this).build()
    }
}