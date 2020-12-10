package com.nyongnsikak.data.source

import javax.inject.Inject


class DataSourceFactory @Inject constructor(
    private val facebookService: FacebookService

) {

    fun remote(): FacebookService = facebookService

}