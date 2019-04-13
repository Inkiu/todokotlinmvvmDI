package com.example.todo_kotlin_mvvm_dagger.domain

import io.reactivex.Single

interface ISingleUseCase <P, R> {
    fun excute(param: P): Single<R>
}