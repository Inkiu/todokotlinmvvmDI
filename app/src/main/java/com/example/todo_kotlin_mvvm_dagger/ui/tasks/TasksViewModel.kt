package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.clean.domain.HelloUseCase

class TasksViewModel(
    private val helloUseCase: HelloUseCase
) : BaseViewModel() {

    val helloLiveData = MutableLiveData<String>()

    override fun onCreate() {
        helloUseCase.invoke(HelloUseCase.Param(1))
            .subscribe { result ->
                when (result) {
                    is HelloUseCase.Result.Success -> helloLiveData.value = result.hello.greetingMessage
                    is HelloUseCase.Result.Failure -> helloLiveData.value = "Error"
                }
            }.composite()
    }
}