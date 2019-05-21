package com.example.todo_kotlin_mvvm_dagger.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.taskdetail_frag.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true

        observe(viewModel)
    }

    private fun observe(viewModel: TaskDetailViewModel) {
        viewModel.error.observe(this, Observer {
            task_detail_title.visibility = View.GONE
            task_detail_description.visibility = View.GONE
        })
        viewModel.loadingState.observe(this, Observer {
            task_detail_title.visibility = View.GONE
            task_detail_description.visibility = View.VISIBLE
            if (it) task_detail_description.text = getString(R.string.loading)
        })
        viewModel.currentTask.observe(this, Observer {
            task_detail_title.visibility = View.VISIBLE
            task_detail_description.visibility = View.VISIBLE
            task_detail_title.text = it.title
            task_detail_description.text = it.description
        })

    }
}