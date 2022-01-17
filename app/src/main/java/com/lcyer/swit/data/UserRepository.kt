package com.lcyer.swit.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Flow<List<User>>
    suspend fun getUsers(since: Int): Flow<PagingData<User>>
    suspend fun getBookMarkUsers(): Flow<List<User>>
    suspend fun bookMarkUser(user: User)
    suspend fun searchUser(searchFlow: Flow<String>): Flow<List<User>>
    //suspend fun searchBookMarkUser(searchFlow: Flow<String>): Flow<List<User>>
}

class UserRepositoryImpl(
    private val userPagingDataSource: UserPagingDataSource,
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUsers(since: Int): Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 30)
    ) {
        userPagingDataSource
    }.flow

    override suspend fun bookMarkUser(user: User) = userDataSource.bookMarkUser(user)

    override suspend fun getUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookMarkUsers(): Flow<List<User>> = userDataSource.getBookMarkUsers()

    override suspend fun searchUser(searchFlow: Flow<String>): Flow<List<User>> =
        userDataSource.searchUser(searchFlow)
}