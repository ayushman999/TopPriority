package com.ayushman999.toppriority

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos_column")
class Todo (
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "isChecked")
    var isChecked: Boolean,
        ){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}