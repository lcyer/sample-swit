package com.lcyer.swit


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {

    object User : Screen(
        route = "user_screen",
        title = "유저 목록",
        icon = Icons.Filled.AccountCircle
    )

    object BookMark : Screen(
        route = "book_mark_screen",
        title = "즐찾 목록",
        icon = Icons.Filled.Favorite
    )
}