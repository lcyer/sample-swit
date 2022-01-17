package com.lcyer.swit.ui.bookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcyer.swit.data.User
import com.lcyer.swit.domain.GetBookMarkUsersUseCase
import com.lcyer.swit.domain.SearchUserUseCase
import com.lcyer.swit.result.data
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookMarkViewModel(
    private val getBookMarkUsersUseCase: GetBookMarkUsersUseCase,
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    private var _bookMarkUsers = MutableLiveData<List<User>>()
    val bookMarkUsers: LiveData<List<User>> = _bookMarkUsers

    init {
        viewModelScope.launch {
            getBookMarkUsersUseCase(Unit).data?.let {
                it.collect {
                    _bookMarkUsers.value = it
                }
            }
        }
    }

    fun searchUser(name: String) {
        viewModelScope.launch {
            searchUserUseCase(name).data?.collect {
                _bookMarkUsers.value = it
            }
        }
    }
}