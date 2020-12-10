package com.nyongnsikak.data.source

import com.nyongnsikak.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface FacebookService {
   // fun loginToGetAccessToken(): Flow<FacebookAccessToken>

    fun login(): Flow<UserData>

    fun logout(): Flow<Boolean>
}