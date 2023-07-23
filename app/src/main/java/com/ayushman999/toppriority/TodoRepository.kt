package com.ayushman999.toppriority

import androidx.lifecycle.LiveData


class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.delete(todo)
    }
}