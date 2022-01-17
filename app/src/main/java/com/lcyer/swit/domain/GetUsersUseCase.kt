package com.lcyer.swit.domain

import androidx.paging.PagingData
import com.lcyer.swit.data.User
import com.lcyer.swit.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(
    private val repository: UserRepository
) : UseCase<Int, Flow<PagingData<User>>>(Dispatchers.IO) {

    override suspend fun execute(parameters: Int): Flow<PagingData<User>> =
        repository.getUsers(parameters)
}