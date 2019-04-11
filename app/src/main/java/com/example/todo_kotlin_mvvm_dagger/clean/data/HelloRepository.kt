package com.example.todo_kotlin_mvvm_dagger.clean.data

import com.example.todo_kotlin_mvvm_dagger.clean.domain.IHelloRepository
import com.example.todo_kotlin_mvvm_dagger.clean.model.Hello
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloRepository @Inject constructor(

) : IHelloRepository {
    override fun getHello(count: Int): Single<Hello> {
        return Single.fromCallable { Hello("Hello? $count") }
    }
}