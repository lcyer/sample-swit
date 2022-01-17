package com.lcyer.swit.di

import com.lcyer.swit.domain.*
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

    factory {
        SearchBookMarkUserUseCase(get())
    }
}
