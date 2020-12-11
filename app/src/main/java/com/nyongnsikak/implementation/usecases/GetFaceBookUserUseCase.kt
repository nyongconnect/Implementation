package com.nyongnsikak.implementation.usecases

import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.login.LoginFragment
import com.facebook.login.LoginManager
import com.google.gson.Gson
import com.nyongnsikak.implementation.ui.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFaceBookUserUseCase @Inject constructor(
    private val gson: Gson
) {

    fun getFaceBookUser(
        accessToken: AccessToken?
    ): Flow<User> {

        return flow {
            val response = GraphRequest.newMeRequest(accessToken) { _, _ -> }.apply {
                val params = Bundle()
                params.putString("fields", "id,name,email")
                parameters = params
            }.executeAndWait()
            val userJson = response.jsonObject
            val user = gson.fromJson(userJson.toString(), User::class.java)
            emit(user)
        }


    }
}