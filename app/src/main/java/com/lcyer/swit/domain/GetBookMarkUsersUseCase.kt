package com.lcyer.swit.domain

import com.lcyer.swit.data.User
import com.lcyer.swit.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class GetBookMarkUsersUseCase(
    private val repository: UserRepository
) : UseCase<Unit, Flow<List<User>>>(Dispatchers.IO) {
    override suspend fun execute(parameters: Unit): Flow<List<User>> = repository.getBookMarkUsers()
}