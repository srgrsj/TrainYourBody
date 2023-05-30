package com.srgrsj.tyb.data.exercise.repository

import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.srgrsj.tyb.domain.exercise.repository.ExerciseRepository
import com.srgrsj.tyb.domain.extenisons.await
import com.srgrsj.tyb.data.firebase.auth.AccountData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ExerciseRepositoryFirebaseImpl
@Inject constructor(private val databaseReference: DatabaseReference) : ExerciseRepository {
    private val exerciseDatabaseReference =
        databaseReference.child("${AccountData.ID}/exercise")

    override suspend fun getExercises(): List<Exercise>? {
        val dataSnapshot = exerciseDatabaseReference.get().await()

        val t: GenericTypeIndicator<List<Exercise>> =
            object :
                GenericTypeIndicator<List<Exercise>>() {}

        return dataSnapshot.getValue(t)

    }

    override suspend fun insertExercise(exercise: Exercise) {
        exerciseDatabaseReference.child(exercise.id).setValue(exercise)
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        exerciseDatabaseReference.child(exercise.id).removeValue()
    }
}
