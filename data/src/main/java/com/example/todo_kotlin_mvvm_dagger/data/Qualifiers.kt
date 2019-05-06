package com.example.todo_kotlin_mvvm_dagger.data

import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContext