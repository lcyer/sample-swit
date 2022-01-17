package com.lcyer.swit.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(since: Int): Flow<PagingData<User>>
}

class UserRepositoryImpl(
    private val userPagingDataSource: UserPagingDataSource
) : UserRepository {

    override suspend fun getUsers(since: Int): Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 30)
    ) {
        userPagingDataSource
    }.flow
}