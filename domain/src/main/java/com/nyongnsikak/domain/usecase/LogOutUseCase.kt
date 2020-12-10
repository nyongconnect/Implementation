package com.nyongnsikak.domain.usecase

import com.nyongnsikak.domain.model.UserDomain
import com.nyongnsikak.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogOutUseCase@Inject constructor(
    private val userRepository: UserRepository
) :FlowUseCase<Boolean, Unit>() {
    override fun buildFlowUseCase(params: Unit?): Flow<Boolean> {
        return userRepository.logout()
    }
}