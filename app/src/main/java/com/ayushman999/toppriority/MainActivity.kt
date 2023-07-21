package com.ayushman999.toppriority

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvTodo: RecyclerView
    lateinit var editText: EditText
    lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodo = findViewById(R.id.priorityList)
        editText = findViewById(R.id.eTaddItem)
        addButton = findViewById(R.id.addBtn)

        var todoList = mutableListOf(
            Todo("Learn Android", false),
            Todo("Check Flipkart", false),
            Todo("Check Amazon", false),
            Todo("Create Top Priority", false),
        )

        val adapter = TodoAdapter(todoList)
        rvTodo.adapter = adapter
        rvTodo.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val title = editText.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
            adapter.notifyItemInserted(todoList.size-1)
        }

    }
}