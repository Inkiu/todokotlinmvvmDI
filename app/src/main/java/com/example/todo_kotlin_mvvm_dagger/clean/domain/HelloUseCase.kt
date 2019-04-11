package com.example.todo_kotlin_mvvm_dagger.clean.domain

import com.example.todo_kotlin_mvvm_dagger.clean.model.Hello
import io.reactivex.Single
import javax.inject.Inject

class HelloUseCase @Inject constructor(
    private val helloRepository: IHelloRepository
) {
    operator fun invoke(param: Param): Single<Result> {
        return helloRepository.getHello(param.count)
            .map { Result.Success(it) }
            .cast(Result::class.java)
            .onErrorReturn { Result.Failure(it) }
    }

    data class Param(val count: Int)
    sealed class Result {
        class Success(val hello: Hello) : Result()
        class Failure(val e: Throwable) : Result()
    }
}
