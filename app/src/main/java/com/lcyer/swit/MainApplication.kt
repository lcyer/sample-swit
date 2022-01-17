package com.lcyer.swit

import android.app.Application
import com.lcyer.swit.di.domainModule
import com.lcyer.swit.di.githubModule
import com.lcyer.swit.di.repoModule
import com.lcyer.swit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                githubModule,
                domainModule,
                repoModule,
                viewModelModule
            )
        }
    }
}