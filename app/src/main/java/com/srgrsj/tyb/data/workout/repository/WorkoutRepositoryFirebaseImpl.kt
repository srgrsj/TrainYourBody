package com.srgrsj.tyb.data.workout.repository

import com.srgrsj.tyb.domain.extensions.await
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.repository.WorkoutRepository
import com.srgrsj.tyb.data.firebase.auth.AccountData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class WorkoutRepositoryFirebaseImpl
@Inject constructor(private val databaseReference: DatabaseReference) : WorkoutRepository {
    private val workoutDatabaseReference =
        databaseReference.child("users/${AccountData.ID}/workout")

//    override suspend fun getWorkouts(): List<Workout>? {
//        val dataSnapshot = workoutDatabaseReference.get().await()
//
//        val t: GenericTypeIndicator<List<Workout>> =
//            object :
//                GenericTypeIndicator<List<Workout>>() {}
//
//        return dataSnapshot.getValue(t)
//    }


    override suspend fun getWorkouts(): List<Workout>? {
        val workoutList = mutableListOf<Workout>()
        print("workouts1")
        val dataSnapshot = workoutDatabaseReference.get().await()
        print("workouts2")
//        dataSnapshot.children.forEach {
//            workoutList.add(it.value as Workout)
//        }
        println(dataSnapshot)

        return workoutList
    }

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