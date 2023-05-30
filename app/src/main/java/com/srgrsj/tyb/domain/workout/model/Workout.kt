package com.srgrsj.tyb.domain.workout.model

import android.os.Parcelable
import com.srgrsj.tyb.domain.exercise.model.Exercise
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Workout(
    var title: String? = "",
    var description: String? = "",
    var duration: Long? = null,
    var isInFav: Boolean? = false,
    var workoutGenerationType: WorkoutGenerationType = WorkoutGenerationType.USER,
    var exerciseList: List<Exercise> = listOf(),
    val id: String? = UUID.randomUUID().toString()
): Parcelable

enum class WorkoutGenerationType {
    AUTHOR, USER, GPT
}
