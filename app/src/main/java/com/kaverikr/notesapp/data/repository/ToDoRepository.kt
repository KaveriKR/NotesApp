package com.kaverikr.notesapp.data.repository

import androidx.lifecycle.LiveData
import com.kaverikr.notesapp.data.ToDoDao
import com.kaverikr.notesapp.data.models.ToDoData

class ToDoRepository (private val toDoDao: ToDoDao){

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }


}