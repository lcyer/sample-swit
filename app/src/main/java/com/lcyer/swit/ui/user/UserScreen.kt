package com.lcyer.swit.ui.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.lcyer.swit.ui.SearchTextField
import com.lcyer.swit.ui.UserListScreen
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.get

@ExperimentalComposeUiApi
@Composable
fun UserScreen(
    navController: NavController,
    userViewModel: UserViewModel = get<UserViewModel>()
) {
    val users = userViewModel
        .users
        .observeAsState(initial = flow {})
        .value
        .collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp
            )
    ) {

        SearchTextField {
            userViewModel.searchUser(it)
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            users.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    loadState.append is LoadState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                        )
                    }
                    loadState.append is LoadState.Error -> {}
                }
            }
            UserListScreen(
                users = users
            ) {
                userViewModel.bookMarkUser(it)
            }
        }

    }
}