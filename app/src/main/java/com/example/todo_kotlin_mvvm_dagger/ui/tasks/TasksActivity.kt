package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.clean.domain.HelloUseCase
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_tasks.*
import javax.inject.Inject

class TasksActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: TasksViewModelFactory
    lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TasksViewModel::class.java)
            .also { observe(it) }
        viewModel.onCreate()
    }

    private fun observe(viewModel: TasksViewModel) {
        viewModel.helloLiveData.observe(this, Observer { taskTextView.text = it })
    }
}
