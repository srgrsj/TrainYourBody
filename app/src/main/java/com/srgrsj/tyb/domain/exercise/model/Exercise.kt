package com.srgrsj.tyb.domain.exercise.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

//@Parcelize
//data class Exercise(
//    var title: String?  = "",
//    var description: String? = "",
//    var numberOfRepetitions: Int? = 1,
//    var numberOfCircles: Int? = 1,
//    var durationOfOneCircle: Long? = null,
//    val id: String = UUID.randomUUID().toString()
//): Parcelable

@Parcelize
data class Exercise(
    var title: String? = "",
    var description: String? = "",
    var numberOfRepetitions: Int? = null,
    var numberOfCircles: Int? = null,
    var durationOfOneCircle: Long? = null,
    var durationOfRest: Long? = null,
    var exerciseType: ExerciseType? = null,
    val id: String = UUID.randomUUID().toString()
) : Parcelable

enum class ExerciseType {
    TIME, REPETITION
}
