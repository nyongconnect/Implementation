package com.nyongnsikak.facebook.service

import android.content.Context
import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.google.gson.Gson
import com.nyongnsikak.data.model.UserData
import com.nyongnsikak.data.source.FacebookService
import com.nyongnsikak.facebook.mapper.UserMapper
import com.nyongnsikak.facebook.model.FacebookUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FacebookServiceImpl @Inject constructor(
    private val gson: Gson,
    private val userMapper: UserMapper
) : FacebookService {

    override fun login(): Flow<UserData> {


        var user: FacebookUser? = null
        val accessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(
                oldAccessToken: AccessToken?,
                currentAccessToken: AccessToken?
            ) {
                if (currentAccessToken != null) {
                    user = getUser(currentAccessToken)

                }
            }
        }
        return flow {
            emit(userMapper.mapToDataLayer(user!!))
        }
    }

    private fun getUser(accessToken: AccessToken): FacebookUser{

        var user: FacebookUser? = null
        val request = GraphRequest.newMeRequest(accessToken) { userJson, _ ->
            if (userJson != null) user = gson.fromJson(userJson.toString(), FacebookUser::class.java)
        }
        request.parameters = getParams()
        request.executeAsync()

        return user!!
    }
    private fun getParams(): Bundle {
        return Bundle().apply {
            putString("fields", "id,name,email,gender,birthday")
        }
    }

    override fun logout(): Flow<Boolean> {
        return flow {
            LoginManager.getInstance().logOut()
        }
    }
}


//    override fun loginToGetAccessToken(): Flow<FacebookAccessToken> {
////        return Observable.create(FacebookLoginSubscribe(context))
////            .map { FacebookAccessToken(it.token, it.userId) }
//
//    }


//            val request = GraphRequest.newMeRequest(accessToken) { userJson, _ ->
//                if (userJson != null) {
//                    val user = gson.fromJson(userJson.toString(), FacebookUser::class.java)
//                    if (!it.isDisposed) {
//                        it.onNext(user)
//                    }
//                } else {
//                    it.onError(FacebookGetUserFail())
//                }
//            }
//            request.parameters = getParams()
//            request.executeAsync()