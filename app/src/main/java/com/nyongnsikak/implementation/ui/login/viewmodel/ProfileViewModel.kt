package com.nyongnsikak.implementation.ui.login.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.nyongnsikak.domain.usecase.LogOutUseCase
import com.nyongnsikak.domain.usecase.LoginUseCase
import com.nyongnsikak.implementation.mapper.UserMapper
import com.nyongnsikak.implementation.ui.model.User
import com.nyongnsikak.implementation.utils.Result
import com.nyongnsikak.movieviewer.utils.asLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @ViewModelInject constructor(
    @ApplicationContext private var context : Context,
    private var loginUseCase: LoginUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val userMapper: UserMapper
) : ViewModel() {



     val callbackManager: CallbackManager by lazy {
        CallbackManager.Factory.create()
    }

    private val _user = MutableLiveData<Result<User>>()
    val user = _user.asLiveData()



    private val _logOut = MutableLiveData<Result<Boolean>>()
    val logout =_logOut.asLiveData()



    fun login(){
        //LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))

//        viewModelScope.launch {
//            loginUseCase.execute()
//                .onStart {  }
//                .catch {  }
//                .collect{
//                    _user.postValue(Result.Success(userMapper.mapToPresentationLayer(it)))
//
//                }
//
//        }
    }


    fun logout(){
        viewModelScope.launch {
           logOutUseCase.execute()
                .onStart {  }
                .catch {  }
                .collect{
                    _logOut.postValue(Result.Success(it))

                }

        }
    }


    fun loginCallback(){

        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                Toast.makeText(context, loginResult?.accessToken?.userId, Toast.LENGTH_LONG).show()
                // Get User's Info
            }

            override fun onCancel() {
                Toast.makeText(context, "Login Cancelled", Toast.LENGTH_LONG).show()
            }

            override fun onError(exception: FacebookException) {
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
            }

        })


    }

}