package com.lcyer.swit.di

import com.lcyer.swit.domain.GetUsersUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetUsersUseCase> {
        GetUsersUseCase(get())
    }
}
