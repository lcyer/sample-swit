package com.lcyer.swit.di


import com.lcyer.swit.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        UserViewModel(get())
    }
}