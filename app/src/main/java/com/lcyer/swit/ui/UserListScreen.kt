package com.lcyer.swit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.lcyer.swit.data.User

@Composable
fun UserListScreen(
    users: LazyPagingItems<User>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(users) { user ->
            user?.let {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        painter = rememberImagePainter(
                            data = user.avatar_url
                        ),
                        contentDescription = null
                    )

                    Text(
                        text = user.login,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}