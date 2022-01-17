package com.lcyer.swit.ui.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.lcyer.swit.ui.SearchTextField
import org.koin.androidx.compose.get

@Composable
fun BookMarkScreen(
    navController: NavController,
    bookMarkViewModel: BookMarkViewModel = get<BookMarkViewModel>()
) {

    val bookMarkUsers = bookMarkViewModel.bookMarkUsers.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTextField {
            bookMarkViewModel.searchUser(it)
        }

        bookMarkUsers.value?.let {
            BookMarkListScreen(it)
        }
    }
}