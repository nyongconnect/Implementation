package com.nyongnsikak.domain.repository

import com.nyongnsikak.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(): Flow<UserDomain>

    fun logout(): Flow<Boolean>
}