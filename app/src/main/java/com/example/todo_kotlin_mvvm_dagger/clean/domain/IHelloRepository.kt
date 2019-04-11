package com.example.todo_kotlin_mvvm_dagger.clean.domain

import com.example.todo_kotlin_mvvm_dagger.clean.model.Hello
import io.reactivex.Single

interface IHelloRepository {
    fun getHello(count: Int): Single<Hello>
}