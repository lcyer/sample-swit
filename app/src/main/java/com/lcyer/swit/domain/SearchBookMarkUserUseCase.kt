package com.lcyer.swit.domain

import com.lcyer.swit.data.User
import com.lcyer.swit.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchBookMarkUserUseCase(
    private val repository: UserRepository
) : UseCase<String, Flow<List<User>>>(Dispatchers.IO) {
    override suspend fun execute(parameters: String): Flow<List<User>> {
        val searchFlow = flow<String> {
            emit(parameters)
        }
        return repository.searchUser(
            searchFlow = searchFlow
        ).map { users ->
            users.filter { it.book_mark }
        }
    }
}