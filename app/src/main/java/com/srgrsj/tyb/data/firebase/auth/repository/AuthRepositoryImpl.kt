package com.srgrsj.tyb.data.firebase.auth.repository

import com.srgrsj.tyb.data.firebase.auth.AccountData
import com.srgrsj.tyb.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.srgrsj.tyb.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class  AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun singOutUser() {
        firebaseAuth.signOut()
        AccountData.EMAIL = null
        AccountData.ID = null
    }
}