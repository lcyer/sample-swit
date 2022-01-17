package com.lcyer.swit.di

import com.lcyer.swit.domain.BookMarkUserUseCase
import com.lcyer.swit.domain.GetBookMarkUsersUseCase
import com.lcyer.swit.domain.GetUsersUseCase
import com.lcyer.swit.domain.SearchUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetUsersUseCase(get())
    }

    factory {
        GetBookMarkUsersUseCase(get())
    }

    factory {
        BookMarkUserUseCase(get())
    }

    factory {
        SearchUserUseCase(get())
    }
}
