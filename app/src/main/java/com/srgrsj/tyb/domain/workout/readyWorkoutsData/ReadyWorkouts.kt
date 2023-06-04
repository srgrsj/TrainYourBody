package com.srgrsj.tyb.domain.workout.readyWorkoutsData

import android.content.Context
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.exercise.model.ExerciseType
import com.srgrsj.tyb.domain.exercise.readyExercisesData.ReadyExercises
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.model.WorkoutGenerationType

enum class ReadyWorkouts(val workout: Workout) {
    WorkoutForBeginner(
        Workout(
            workoutGenerationType = WorkoutGenerationType.AUTHOR
        )
    ),
    WorkoutForIntermediate(
        Workout(
            workoutGenerationType = WorkoutGenerationType.AUTHOR
        )
    ),
    WorkoutForPro(
        Workout(
            workoutGenerationType = WorkoutGenerationType.AUTHOR
        )
    );

    fun getLocalizedWorkout(context: Context): Workout {
        when (this) {
            WorkoutForBeginner -> return this.workout.copy(
                title = context.getString(R.string.beginners_workout),
                description = context.getString(R.string.beginners_workout_description),
                duration = 30 * 1000 * 60,
                exerciseList = listOf(
                    ReadyExercises.PUSH_UPS.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 12,
                        numberOfCircles = 2,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.SQUATS.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 12,
                        numberOfCircles = 2,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.PLANK.getLocalizedExercise(context).copy(
                        durationOfOneCircle = 30 * 1000,
                        numberOfCircles = 2,
                        exerciseType = ExerciseType.TIME
                    ),
                    ReadyExercises.PULL_UPS.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 5,
                        numberOfCircles = 2,
                        exerciseType = ExerciseType.REPETITION
                    )
                )
            )

            WorkoutForIntermediate -> return this.workout.copy(
                title = context.getString(R.string.intermediate_workout),
                description = context.getString(R.string.intermediate_workout_description),
                duration = 45 * 1000 * 60,
                exerciseList = listOf(
                    ReadyExercises.DUMBBELL_PRESS.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 8,
                        numberOfCircles = 3,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.DUMBBELL_LATERAL_RAISES.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 10,
                        numberOfCircles = 3,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.BENT_OVER_DUMBBELL_ROWS.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 12,
                        numberOfCircles = 3,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.DUMBBELL_BICEP_CURLS.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 10,
                        numberOfCircles = 3,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.REGULAR_CRUNCH.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 15,
                        numberOfCircles = 3,
                        exerciseType = ExerciseType.REPETITION
                    )
                )
            )

            WorkoutForPro -> return this.workout.copy(
                title = context.getString(R.string.experienced_workout),
                description = context.getString(R.string.experienced_workout_description),
                duration = 60 * 1000 * 60,
                exerciseList = listOf(
                    ReadyExercises.DIPS.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 12,
                        numberOfCircles = 4,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.LUNGES.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 10,
                        numberOfCircles = 4,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.HYPEREXTENSION.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 12,
                        numberOfCircles = 4,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.LEG_RAISES.getLocalizedExercise(context).copy(
                        numberOfRepetitions = 8,
                        numberOfCircles = 4,
                        exerciseType = ExerciseType.REPETITION
                    ),
                    ReadyExercises.PLANK_WITH_SHOULDER_TAPS.getLocalizedExercise(context).copy(
                        durationOfOneCircle = 30 * 1000,
                        numberOfCircles = 4,
                        exerciseType = ExerciseType.TIME
                    )
                )
            )
        }
    }
}
