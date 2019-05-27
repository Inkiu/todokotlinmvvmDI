package com.example.todo_kotlin_mvvm_dagger.data.repo

import android.content.Context
import com.example.todo_kotlin_mvvm_dagger.data.ApplicationContext
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

class TaskRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : ITaskRepository {

    private val tasksDatabase = mutableMapOf(
        0L to Task(0, "TEST 1", "This is fake task. 1", true),
        1L to Task(1, "TEST 2", "This is fake task. 2", false)
    )
    private val uuidMaker = AtomicLong(tasksDatabase.size.toLong())

    override fun loadTasks(filter: TaskFilterType): Single<List<Task>> {
        return Single.fromCallable {
            tasksDatabase.values.filter {
                when (filter) {
                    TaskFilterType.ALL_TASKS -> true
                    TaskFilterType.COMPLETED_TASKS -> it.completed
                    TaskFilterType.ACTIVE_TASKS -> !it.completed
                }
            }
        }
    }

    override fun loadTask(taskId: Long): Single<Task> {
        return Single.fromCallable {
            tasksDatabase[taskId]
        }
    }

    override fun saveTasks(tasks: List<Task>): Completable {
        return Completable.fromAction {
            tasks.forEach {
                val uuid = uuidMaker.getAndIncrement()
                tasksDatabase[uuid] = it.copy(uuid = uuid)
            }
        }
    }

    override fun deleteTasks(taskIdList: List<Long>): Completable {
        return Completable.fromAction {
            taskIdList.forEach { tasksDatabase.remove(it) }
        }
    }

    override fun completeTask(taskId: Long): Completable {
        return Completable.fromAction {
            tasksDatabase[taskId]?.copy(completed = true)?.let {
                tasksDatabase[taskId] = it
            }
        }
    }

    override fun activeTask(taskId: Long): Completable {
        return Completable.fromAction {
            tasksDatabase[taskId]?.copy(completed = false)?.let {
                tasksDatabase[taskId] = it
            }
        }
    }

    override fun updateTask(task: Task): Completable {
        return Completable.fromAction {
            tasksDatabase[task.uuid] = task.copy()
        }
    }
}