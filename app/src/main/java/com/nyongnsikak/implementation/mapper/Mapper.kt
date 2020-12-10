package com.nyongnsikak.implementation.mapper

interface Mapper<I, O> {
    fun mapToPresentationLayer(data: I): O
}
