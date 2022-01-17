package com.lcyer.swit.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPagingDataSource(
    private val githubApi: GithubApi,
    private val userDao: UserDao
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val users = githubApi.getUsers(
            since = params.key ?: 0
        )

        CoroutineScope(Dispatchers.IO).launch {
            val bookMarkUsers = userDao
                .getUsers()
                .filter { it.book_mark }

            val result = users.map {
                bookMarkUsers.forEach { bookMarkUser ->
                    if (it.id == bookMarkUser.id) {
                        it.book_mark = bookMarkUser.book_mark
                    }
                }
                it
            }
            userDao.insertAll(result)
        }

        return LoadResult.Page(
            users,
            prevKey = params.key,
            nextKey = users[users.size - 1].id
        )
    }
}