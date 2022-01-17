package com.lcyer.swit.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class UserPagingDataSource(
    private val githubApi: GithubApi
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val users = githubApi.getUsers(
            since = params.key ?: 0
        )

        return LoadResult.Page(
            users,
            prevKey = params.key,
            nextKey = users[users.size - 1].id
        )
    }
}