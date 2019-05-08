package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class AddEditViewModelFactory @Inject constructor(

) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditViewModel::class.java)) {
            return AddEditViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}