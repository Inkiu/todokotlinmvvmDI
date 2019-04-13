package com.example.todo_kotlin_mvvm_dagger.domain

import io.reactivex.Single

interface ISingleUseCase <P, R> {
    operator fun invoke(param: P): Single<R>
}