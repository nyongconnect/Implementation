package com.nyongnsikak.facebook.mapper

interface Mapper<in R, D> {
    fun mapToDataLayer(data: R): D
}
