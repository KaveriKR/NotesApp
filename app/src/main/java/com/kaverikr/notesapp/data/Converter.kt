package com.kaverikr.notesapp.data

import androidx.room.TypeConverter
import com.kaverikr.notesapp.data.models.Priority


class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return priority.name
    }

    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}