package com.example.todo_kotlin_mvvm_dagger.ui.detail

import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTask

class TaskDetailViewModel(
    private val getTask: GetTask
) :  BaseViewModel() {

}