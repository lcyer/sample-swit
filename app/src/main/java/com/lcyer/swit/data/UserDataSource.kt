package com.lcyer.swit.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface UserDataSource {
    suspend fun bookMarkUser(user: User)
    suspend fun getUsers(): Flow<List<User>>
    suspend fun getBookMarkUsers(): Flow<List<User>>
    suspend fun searchUser(searchFlow: Flow<String>): Flow<List<User>>
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

    override suspend fun searchUser(searchFlow: Flow<String>) = flow {
        searchFlow
            .debounce(300)
            .collect {
                val searchUsers = userDao
                    .getUsers()
                    .filter { user -> user.book_mark && user.login.contains(it) }
                    .also { Log.d("cylee", "find users -> $it") }
                emit(searchUsers)
            }
    }.flowOn(Dispatchers.IO)
}