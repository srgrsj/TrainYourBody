package com.srgrsj.tyb.domain.exercise.typeConverter

import androidx.room.TypeConverter
import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExerciseConverters {
    @TypeConverter
    fun fromExercise(exerciseList: List<Exercise>): String {
        val gson = Gson()
        return gson.toJson(exerciseList)
    }

    @TypeConverter
    fun toExercise(data: String): List<Exercise> {
        val gson = Gson()
        val listType = object : TypeToken<List<Exercise>>() {}.type
        return gson.fromJson(data, listType)
    }
}