package com.nyongnsikak.data.repository

import com.nyongnsikak.data.mapper.UserMapper
import com.nyongnsikak.data.source.FacebookService
import com.nyongnsikak.domain.model.UserDomain
import com.nyongnsikak.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val facebookService: FacebookService,
    private val userMapper: UserMapper

) : UserRepository {

    override fun login(): Flow<UserDomain> {
        return facebookService.login().map {
            userMapper.mapToDomainLayer(it)
        }
        }

    override fun logout(): Flow<Boolean> {
        return facebookService.logout()
    }
}