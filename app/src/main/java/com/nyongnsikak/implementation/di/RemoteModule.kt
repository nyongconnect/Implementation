package com.nyongnsikak.movieviewer.di

import com.nyongnsikak.data.source.FacebookService
import com.nyongnsikak.facebook.service.FacebookServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteModule {
    @Binds
    @Singleton
    abstract fun facebookService(facebookServiceImpl: FacebookServiceImpl): FacebookService
}