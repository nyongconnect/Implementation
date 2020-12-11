package com.nyongnsikak.implementation.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.nyongnsikak.implementation.ui.model.User
import com.nyongnsikak.implementation.usecases.GetFaceBookUserUseCase
import com.nyongnsikak.implementation.utils.Result
import com.nyongnsikak.movieviewer.utils.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(
    private var loginUseCase: com.nyongnsikak.implementation.usecases.LoginWithFaceBookUseCase,
    private val getFaceBookUserUseCase: GetFaceBookUserUseCase,
    val callbackManager: CallbackManager,
    private val loginManager: LoginManager
) : ViewModel() {


    private val _user = MutableLiveData<Result<User>>()
    val user = _user.asLiveData()


    private val _logOut = MutableLiveData<Result<Boolean>>()
    val logout = _logOut.asLiveData()


    init {
        registerLoginCallback()
    }

    fun login(loginFragment: LoginFragment) {
        viewModelScope.launch {
            loginUseCase.logInWithFaceBook(loginFragment, listOf("public_profile", "email")) {
                registerLoginCallback()
            }
        }
    }

    private fun getFaceBookUser(accessToken: AccessToken?) {
        viewModelScope.launch {
            getFaceBookUserUseCase
                .getFaceBookUser(accessToken)
                .flowOn(Dispatchers.IO)
                .catch {

                    println("error $it")

                }
                .collect { user ->
                    _user.postValue(Result.Success(user))

                }
        }
    }

    private fun registerLoginCallback() {

        loginManager.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                getFaceBookUser(loginResult?.accessToken)
            }

            override fun onCancel() {
                println("dfghj")
            }

            override fun onError(exception: FacebookException) {
                println(" error $exception")
            }

        })

    }

}