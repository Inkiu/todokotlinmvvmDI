package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@PerActivity
class AddEditTaskFragment @Inject constructor(): DaggerFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.addtask_frag, container, false)
    }
}