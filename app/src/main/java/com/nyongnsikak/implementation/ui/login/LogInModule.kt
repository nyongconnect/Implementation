package com.nyongnsikak.implementation.ui.login

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
object LogInModule {

    @Provides
    fun callBackManager(): CallbackManager {
        return CallbackManager.Factory.create()
    }

    @Provides
    fun loginManager(): LoginManager {
        return LoginManager.getInstance()
    }

}


//val callbackManager: CallbackManager,
//private val loginManager: LoginManager