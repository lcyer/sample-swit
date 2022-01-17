package com.lcyer.swit.data

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int = 0,
        @Query("per_page") perPage: Int = 30
    ): List<User>
}