package com.ayushman999.toppriority

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), TodoRVAdapter {
    lateinit var rvTodo: RecyclerView
    lateinit var editText: EditText
    lateinit var addButton: Button
    lateinit var todoViewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodo = findViewById(R.id.priorityList)
        editText = findViewById(R.id.eTaddItem)
        addButton = findViewById(R.id.addBtn)

        val adapter = TodoAdapter(this,this)
        rvTodo.adapter = adapter
        rvTodo.layoutManager = LinearLayoutManager(this)

        todoViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TodoViewModel::class.java)
        todoViewModel.allTodos.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)
            }
        })



        addButton.setOnClickListener {
            val title = editText.text.toString()
            val todo = Todo(title, "",false)
            if(title.isNotEmpty()){
                Log.d("Insert", "onCreate: inserting")
                todoViewModel.insertTodo(todo)
                adapter.notifyDataSetChanged()
            }
            editText.text.clear()
        }

    }

    override fun onDeleteClicked(todo: Todo) {
        todoViewModel.deleteTodo(todo)
        Log.d("Delete", "onDeleteClicked: ")
    }

    override fun onCheckClicked(todo: Todo) {
        todo.isChecked = !todo.isChecked
        todoViewModel.updateTodo(todo)
        Log.d("Checked", "onCheckClicked: ")

    }
}