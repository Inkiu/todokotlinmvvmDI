package com.example.todo_kotlin_mvvm_dagger.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@PerActivity
class TaskDetailFragment @Inject constructor(): DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: TaskDetailViewModelFactory
    private val viewModel: TaskDetailViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(TaskDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.taskdetail_frag, container, false)
    }

}