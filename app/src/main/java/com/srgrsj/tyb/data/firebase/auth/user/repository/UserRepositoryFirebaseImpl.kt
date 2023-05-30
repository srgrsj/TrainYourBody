package com.srgrsj.tyb.data.firebase.auth.user.repository

import com.srgrsj.tyb.domain.user.model.User
import com.srgrsj.tyb.domain.user.repository.UserRepository
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class UserRepositoryFirebaseImpl
@Inject constructor(private val databaseReference: DatabaseReference) : UserRepository{
    private val userDatabaseReference =
        databaseReference.child("users")

    override suspend fun insertUser(user: User) {
        user.id.let { userDatabaseReference.child(it).setValue(user) }
    }
}