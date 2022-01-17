package com.lcyer.swit.ui.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.lcyer.swit.ui.UserListScreen
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.get

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

        UserListScreen(
            users = users
        )
    }
}