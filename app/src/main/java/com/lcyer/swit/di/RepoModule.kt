package com.lcyer.swit.di

import com.lcyer.swit.AppDataBase
import com.lcyer.swit.data.*
import org.koin.dsl.module

val repoModule = module {

    single<AppDataBase> {
        AppDataBase.buildDataBase(get())
    }

    single<UserDao> {
        get<AppDataBase>().userDao()
    }

    single<UserDataSource> {
        LocalUserDataSource(get())
    }

    single<UserPagingDataSource> {
        UserPagingDataSource(
            get(),
            get<UserDao>()
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
            get(),
            get()
        )
    }
}