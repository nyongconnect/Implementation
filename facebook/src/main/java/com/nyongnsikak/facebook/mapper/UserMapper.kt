package com.nyongnsikak.facebook.mapper

import com.nyongnsikak.data.model.UserData
import com.nyongnsikak.facebook.model.FacebookUser
import javax.inject.Inject

class UserMapper @Inject constructor(): Mapper<FacebookUser, UserData> {
    override fun mapToDataLayer(data: FacebookUser): UserData {
        return with(data){
            UserData(
                id,
                name,
                email,
                birthday
            )
        }
    }
}