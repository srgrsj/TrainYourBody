package com.srgrsj.tyb.di

import com.srgrsj.tyb.data.exercise.repository.ExerciseRepositoryFirebaseImpl
import com.srgrsj.tyb.data.firebase.auth.user.repository.UserRepositoryFirebaseImpl
import com.srgrsj.tyb.data.workout.repository.WorkoutRepositoryFirebaseImpl
import com.srgrsj.tyb.domain.exercise.usecase.AddExerciseUseCase
import com.srgrsj.tyb.domain.exercise.usecase.DeleteExerciseUseCase
import com.srgrsj.tyb.domain.exercise.usecase.ExerciseUseCase
import com.srgrsj.tyb.domain.exercise.usecase.GetExercisesUseCase
import com.srgrsj.tyb.domain.user.usecases.AddUserUseCase
import com.srgrsj.tyb.domain.user.usecases.UserUseCase
import com.srgrsj.tyb.domain.workout.usecases.AddWorkoutUseCase
import com.srgrsj.tyb.domain.workout.usecases.ChangeWorkoutFavState
import com.srgrsj.tyb.domain.workout.usecases.DeleteWorkoutUseCase
import com.srgrsj.tyb.domain.workout.usecases.GetWorkoutsUseCase
import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun providesExerciseUseCases(exerciseRepositoryFirebaseImpl: ExerciseRepositoryFirebaseImpl): ExerciseUseCase =
        ExerciseUseCase(
            addExerciseUseCase = AddExerciseUseCase(exerciseRepositoryFirebaseImpl),
            getExercisesUseCase = GetExercisesUseCase(exerciseRepositoryFirebaseImpl),
            deleteExerciseUseCase = DeleteExerciseUseCase(exerciseRepositoryFirebaseImpl)
        )

    @Provides
    fun providesWorkoutUseCases(workoutRepositoryFirebaseImpl: WorkoutRepositoryFirebaseImpl): WorkoutUseCase =
        WorkoutUseCase(
            addWorkoutUseCase = AddWorkoutUseCase(workoutRepositoryFirebaseImpl),
            getWorkoutsUseCase = GetWorkoutsUseCase(workoutRepositoryFirebaseImpl),
            deleteWorkoutUseCase = DeleteWorkoutUseCase(workoutRepositoryFirebaseImpl),
            changeWorkoutFavState = ChangeWorkoutFavState(workoutRepositoryFirebaseImpl)
        )

    @Provides
    fun providesUserUseCases(userRepositoryFirebaseImpl: UserRepositoryFirebaseImpl): UserUseCase =
        UserUseCase(
            addUserUseCase = AddUserUseCase(userRepositoryFirebaseImpl)
        )
}