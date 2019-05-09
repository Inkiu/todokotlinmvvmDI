package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.events.Event

class AddEditViewModel(
    private val taskId: String
) : BaseViewModel() {

    /* events */
    val error = MutableLiveData<Event<ErrorType>>()

    fun onSubmit() {
        error.value = Event(ErrorType.EMPTY_TITLE)
    }

    enum class ErrorType {
        EMPTY_TITLE
    }
}