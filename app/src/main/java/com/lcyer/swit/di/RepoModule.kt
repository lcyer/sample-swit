package com.lcyer.swit.di

import com.lcyer.swit.data.UserPagingDataSource
import com.lcyer.swit.data.UserRepository
import com.lcyer.swit.data.UserRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<UserPagingDataSource> {
        UserPagingDataSource(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}