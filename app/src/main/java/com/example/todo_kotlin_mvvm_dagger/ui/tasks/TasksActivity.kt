package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.BaseActivity
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.R
import javax.inject.Inject

class TasksActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: TasksViewModelFactory
    private val viewModel: TasksViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(TasksViewModel::class.java) }

    override fun getViewModel(): BaseViewModel { return viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)
        observe(viewModel)
    }

    private fun observe(viewModel: TasksViewModel) {
//        viewModel.helloLiveData.observe(this, Observer { taskTextView.text = it })
    }
}
