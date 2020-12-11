package com.nyongnsikak.implementation.usecases

import com.facebook.login.LoginManager
import com.nyongnsikak.implementation.ui.login.LoginFragment
import javax.inject.Inject

class LoginWithFaceBookUseCase @Inject constructor(
    private val loginManager: LoginManager
) {

    fun logInWithFaceBook(loginFragment: LoginFragment,permissions: List<String>, callBack: () -> Unit) {
        loginManager.logInWithReadPermissions(loginFragment, permissions)
        callBack()
    }
}