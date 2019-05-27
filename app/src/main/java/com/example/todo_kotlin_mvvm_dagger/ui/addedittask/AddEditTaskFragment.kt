package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.extensions.addSimpleTextChangeListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.addtask_frag.*

class AddEditTaskFragment : Fragment() {

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

        add_task_title.addSimpleTextChangeListener { s, _ -> viewModel.onTitleChange(s.toString()) }
        add_task_description.addSimpleTextChangeListener { s, _ -> viewModel.onContentChange(s.toString()) }

        observe(viewModel)
    }

    private fun observe(viewModel: AddEditViewModel) {
        viewModel.error.observe(this, Observer {
            Snackbar.make(add_task_title, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show()
        })
        viewModel.taskViewModel.observe(this, Observer {
            if (it.title != add_task_title.text.toString()) add_task_title.setText(it.title)
            if (it.content != add_task_description.text.toString()) add_task_description.setText(it.content)
        })
    }
}