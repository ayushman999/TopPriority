package com.ayushman999.toppriority

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val context: Context,
    val listener: TodoRVAdapter
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val allTodos = ArrayList<Todo>()

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView
        val checkBox: CheckBox
        val deleteIcn: ImageView
        init {
            textView = itemView.findViewById(R.id.tvTitle)
            checkBox = itemView.findViewById(R.id.cbDone)
            deleteIcn = itemView.findViewById(R.id.ic_delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.apply {
            textView.text = allTodos[position].title
            checkBox.isChecked = allTodos[position].isChecked
        }
        holder.checkBox.setOnClickListener {
            listener.onCheckClicked(allTodos[position])
        }
        holder.deleteIcn.setOnClickListener{
            listener.onDeleteClicked(allTodos[position])
        }
    }

    override fun getItemCount(): Int {
        return allTodos.size
    }

    fun updateList(list: List<Todo>){
        allTodos.clear()
        allTodos.addAll(list)
        notifyDataSetChanged()
    }

}

interface TodoRVAdapter{
    fun onDeleteClicked(todo: Todo)
    fun onCheckClicked(todo: Todo)
}