package com.nyongnsikak.data.mapper



interface Mapper<I, O> {
    fun mapToDomainLayer(data: I): O
}
