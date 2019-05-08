package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.tasks_act.*
import kotlinx.android.synthetic.main.tasks_frag.*
import javax.inject.Inject

@PerActivity
class TasksFragment @Inject constructor(): DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: TasksViewModelFactory
    private val viewModel: TasksViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(TasksViewModel::class.java)
    }

    private val tasksAdapter = TasksAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tasks_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup Recycler View
        tasks_list.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        tasks_list.adapter = tasksAdapter

        // Setup RefreshLayout
        refresh_layout.setColorSchemeColors(
            ContextCompat.getColor(requireContext(), R.color.colorPrimary),
            ContextCompat.getColor(requireContext(), R.color.colorAccent),
            ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
        )
        refresh_layout.setScrollUpChild(tasks_list)
        refresh_layout.setOnRefreshListener { viewModel.onTaskRefresh() }

        // Option Menu
        setHasOptionsMenu(true)

        // Observe
        observe(viewModel)
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

    private fun observe(viewModel: TasksViewModel) {
        viewModel.taskData.observe(this, Observer {
            tasksLL.visibility = if (it.isNotEmpty()) View.VISIBLE else View.INVISIBLE
            noTasks.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            tasksAdapter.updateTask(it)
        })
        viewModel.loadingState.observe(this, Observer { refresh_layout.isRefreshing = it })
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