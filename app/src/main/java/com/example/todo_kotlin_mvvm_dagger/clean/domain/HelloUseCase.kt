package com.example.todo_kotlin_mvvm_dagger.clean.domain

import com.example.todo_kotlin_mvvm_dagger.clean.model.Hello
import io.reactivex.Single
import javax.inject.Inject

class HelloUseCase @Inject constructor(
    private val helloRepository: IHelloRepository
) {
    operator fun invoke(param: Param): Single<Hello> {
        return helloRepository.getHello(param.count)
    }
    data class Param(val count: Int)
}
