package com.lcyer.swit.ui.user

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
            Log.d("cylee", "text field -> $it")
        }

        UserListScreen(
            users = users
        ) {
            userViewModel.bookMarkUser(it)
        }
    }
}