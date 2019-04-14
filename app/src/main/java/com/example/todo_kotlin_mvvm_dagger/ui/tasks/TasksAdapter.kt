package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import kotlinx.android.synthetic.main.task_item.view.*

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.Holder>() {

    private val taskItems = SortedList<Task>(Task::class.java, TaskSortedCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))
    }

    override fun getItemCount(): Int {
        return taskItems.size()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val task = taskItems[position]
        holder.bind(task)
        holder.bindListener(task)
        // bind Listener
    }

    fun updateTask(tasks: List<Task>) {
        taskItems.beginBatchedUpdates()
        taskItems.clearItemAll()
        taskItems.addItemAll(tasks)
        taskItems.endBatchedUpdates()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            with(itemView) {
                title.text = if (task.title.isEmpty()) task.description else task.title
                complete.isChecked = task.completed
                if (task.completed) {
                    this.setBackgroundResource(R.drawable.list_completed_touch_feedback)
                } else {
                    this.setBackgroundResource(R.drawable.touch_feedback)
                }
            }
        }

        fun bindListener(task: Task) {
            itemView.setOnClickListener { Log.d("tmpLog", "bindListener : $task") }
        }
    }

    inner class TaskSortedCallback : SortedList.Callback<Task>() {
        override fun onMoved(fromPosition: Int, toPosition: Int) = notifyItemMoved(fromPosition, toPosition)
        override fun onChanged(position: Int, count: Int) = notifyItemChanged(position, count)
        override fun onInserted(position: Int, count: Int) = notifyItemRangeInserted(position, count)
        override fun onRemoved(position: Int, count: Int) = notifyItemRangeRemoved(position, count)
        override fun compare(o1: Task?, o2: Task?): Int = (o1?.uuid?.toInt()) ?: 0 - (o2?.uuid?.toInt() ?: 0)
        override fun areItemsTheSame(item1: Task?, item2: Task?): Boolean = item1?.uuid == item2?.uuid
        override fun areContentsTheSame(oldItem: Task?, newItem: Task?): Boolean = oldItem?.uuid == newItem?.uuid
    }

    private fun <T> SortedList<T>.clearItemAll() { while(size() > 0) removeItemAt(size() - 1) }
    private fun <T> SortedList<T>.addItemAll(adding: List<T>) { adding.forEach { add(it) } }
}
