package com.nyongnsikak.implementation.mapper

import com.nyongnsikak.domain.model.UserDomain
import com.nyongnsikak.implementation.ui.model.User
import javax.inject.Inject

class UserMapper @Inject constructor(): Mapper<UserDomain, User> {
    override fun mapToPresentationLayer(data: UserDomain): User {
        return with(data){
            User(
                id,
                name,
                email,
                birthday
            )
        }
    }
}