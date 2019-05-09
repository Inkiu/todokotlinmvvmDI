package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.addtask_frag.*
import javax.inject.Inject

@PerActivity
class AddEditTaskFragment @Inject constructor(): DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: AddEditViewModelFactory
    private val viewModel: AddEditViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(AddEditViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.addtask_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true

        observe(viewModel)
    }

    private fun observe(viewModel: AddEditViewModel) {
        viewModel.error.observe(this, Observer {
            Snackbar.make(add_task_title, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show()
        })
    }
}