package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TasksFragment @Inject constructor(): DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: TasksViewModelFactory
    private val viewModel: TasksViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(TasksViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.tasks_frag, container, false)
        return root
    }
}