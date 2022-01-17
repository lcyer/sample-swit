package com.lcyer.swit.di

import com.lcyer.swit.data.GithubApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val githubModule = module {
    single<GithubApi> {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain
                        .request()
                        .newBuilder()
                        .addHeader("Accept", "application/vnd.github.v3+json")
                        .build()
                )
            }
            .build()

        Retrofit
            .Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GithubApi::class.java)
    }
}
