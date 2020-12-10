package com.nyongnsikak.data.mapper

import com.nyongnsikak.data.model.UserData
import com.nyongnsikak.domain.model.UserDomain
import javax.inject.Inject

class UserMapper @Inject constructor(): Mapper<UserData, UserDomain> {
    override fun mapToDomainLayer(data: UserData): UserDomain {
        return with(data){
            UserDomain(id,
            name,
            email,birthday)
        }
    }
}