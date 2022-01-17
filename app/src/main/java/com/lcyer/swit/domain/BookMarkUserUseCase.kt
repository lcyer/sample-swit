package com.lcyer.swit.domain

import com.lcyer.swit.data.User
import com.lcyer.swit.data.UserRepository
import kotlinx.coroutines.Dispatchers

class BookMarkUserUseCase(
    private val repository: UserRepository
) : UseCase<User, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: User) = repository.bookMarkUser(parameters)
}