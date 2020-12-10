package com.nyongnsikak.implementation.ui.login.viewmodel

import com.nyongnsikak.data.repository.UserRepositoryImpl
import com.nyongnsikak.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UserModule {

    @Binds
    abstract fun userRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}