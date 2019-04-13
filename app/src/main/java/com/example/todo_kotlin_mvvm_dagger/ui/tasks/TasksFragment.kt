package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
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

        setHasOptionsMenu(true)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_clear -> { } // TODO
            R.id.menu_filter -> { showFilteringPopUpMenu() }
            R.id.menu_refresh -> { } // TODO
        }
        return true
    }

    private fun showFilteringPopUpMenu() {
        val popup = PopupMenu(requireContext(), requireActivity().findViewById(R.id.menu_filter))
        popup.menuInflater.inflate(R.menu.filter_tasks, popup.menu)
        popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.active -> {} // TODO
                    R.id.completed -> {} // TODO
                    else -> {} // TODO
                }
                return true
            }
        })
        popup.show()
    }
}