package com.example.todo_kotlin_mvvm_dagger.domain

import io.reactivex.Completable

interface ICompletableUseCase <P> {
    operator fun invoke(param: P): Completable
}