package com.srgrsj.tyb.data.workout.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.srgrsj.tyb.data.user.AccountData
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.repository.WorkoutRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class WorkoutRepositoryFirebaseImpl
@Inject constructor(databaseReference: DatabaseReference) : WorkoutRepository {
    private val workoutDatabaseReference =
        databaseReference.child("users/${AccountData.ID}/workout")

//    override suspend fun getWorkouts(): List<Workout> {
//        return mutableListOf<Workout>()
//    }

    override fun getWorkoutsLegacy(onComplete: ((List<Workout>?) -> Unit)?) {
        val t: GenericTypeIndicator<HashMap<String, Workout>> =
            object :
                GenericTypeIndicator<HashMap<String, Workout>>() {}

        workoutDatabaseReference.get().addOnSuccessListener { dataSnapshot ->
            onComplete?.invoke(dataSnapshot.getValue(t)?.values?.toList())
        }
    }


    override suspend fun insertWorkout(workout: Workout) {
        workout.id?.let { workoutDatabaseReference.child(it).setValue(workout) }
    }

    override suspend fun changeWorkoutFavState(workout: Workout) {
        val isInFavState = !workout.isInFav!!
        workout.id?.let { workoutDatabaseReference.child(it).child("inFav").setValue(isInFavState) }
    }

    override suspend fun deleteWorkout(workout: Workout) {
        workout.id?.let { workoutDatabaseReference.child(it).removeValue() }
    }

    //    override suspend fun getFavWorkouts(): List<Workout>? {
//        val dataSnapshot = workoutDatabaseReference.child("isInFav")
//
//        val t: GenericTypeIndicator<List<Workout>> =
//            object :
//                GenericTypeIndicator<List<Workout>>() {}
//
//        return dataSnapshot.getValue(t)
//    }
}