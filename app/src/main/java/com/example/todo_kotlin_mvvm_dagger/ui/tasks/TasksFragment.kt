package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import kotlinx.android.synthetic.main.tasks_frag.*

class TasksFragment : Fragment(), TasksAdapter.TaskItemListener {

    lateinit var viewModelFactory: TasksViewModelFactory
    private val viewModel: TasksViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(TasksViewModel::class.java)
    }

    private val tasksAdapter = TasksAdapter(this)

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
            R.id.menu_clear -> { viewModel.onClearTask() }
            R.id.menu_filter -> { showFilteringPopUpMenu() }
            R.id.menu_refresh -> { viewModel.onTaskRefresh() }
        }
        return true
    }

    override fun onTaskClick(clickedTask: Task) {
        viewModel.onTaskSelected(clickedTask)
    }

    override fun onTaskButtonClick(clickedTask: Task) {
        viewModel.onTaskButtonSelected(clickedTask)
    }

    private fun observe(viewModel: TasksViewModel) {
        viewModel.taskData.observe(this, Observer {
            tasksLL.visibility = if (it.isNotEmpty()) View.VISIBLE else View.INVISIBLE
            noTasks.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            tasksAdapter.updateTask(it)
        })
        viewModel.loadingState.observe(this, Observer { refresh_layout.isRefreshing = it })
        viewModel.currentFilterType.observe(this, Observer { changeFilterNotice(it) })
    }

    private fun changeFilterNotice(filter: TaskFilterType) {
        val textId = when (filter) {
            TaskFilterType.ALL_TASKS -> R.string.label_all
            TaskFilterType.COMPLETED_TASKS -> R.string.label_completed
            TaskFilterType.ACTIVE_TASKS -> R.string.label_active
        }
        filteringLabel.text = resources.getString(textId)
    }

    private fun showFilteringPopUpMenu() {
        val popup = PopupMenu(requireContext(), requireActivity().findViewById(R.id.menu_filter))
        popup.menuInflater.inflate(R.menu.filter_tasks, popup.menu)
        popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                val filterType = when (item.itemId) {
                    R.id.active -> TaskFilterType.ACTIVE_TASKS
                    R.id.completed -> TaskFilterType.COMPLETED_TASKS
                    else -> TaskFilterType.ALL_TASKS
                }
                viewModel.onFilterSelected(filterType)
                return true
            }
        })
        popup.show()
    }

}