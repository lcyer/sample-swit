package com.lcyer.swit.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface UserDataSource {
    suspend fun bookMarkUser(user: User)
    suspend fun getUsers(): Flow<List<User>>
    suspend fun getBookMarkUsers(): Flow<List<User>>
}

class LocalUserDataSource(
    private val userDao: UserDao
) : UserDataSource {
    override suspend fun getUsers(): Flow<List<User>> {
        val result = userDao.getUsers()
        return flowOf(result)
    }

    override suspend fun getBookMarkUsers(): Flow<List<User>> {
        return flowOf(
            userDao
                .getUsers()
                .filter { it.book_mark }
        )
    }

    override suspend fun bookMarkUser(user: User) = userDao.update(user)
}