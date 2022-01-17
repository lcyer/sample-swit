package com.lcyer.swit.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.lcyer.swit.data.User
import com.lcyer.swit.domain.GetUsersUseCase
import com.lcyer.swit.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<Flow<PagingData<User>>>()
    val users: LiveData<Flow<PagingData<User>>> = _users

    init {
        viewModelScope.launch {
            getUsersUseCase(0).data?.let {
                _users.value = it
            }
        }
    }
}