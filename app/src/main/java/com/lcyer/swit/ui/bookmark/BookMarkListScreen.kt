package com.lcyer.swit.ui.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.lcyer.swit.data.User

@Composable
fun BookMarkListScreen(
    bookMarkUsers: List<User>,
    onBookMarkClick: (User) -> Unit
) {
    var bookMarkState by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(bookMarkUsers) { user ->
            user?.let {
                bookMarkState = it.book_mark
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .padding(end = 15.dp)
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

                    Box(
                        modifier = Modifier
                            .padding(
                                end = 15.dp
                            )
                            .fillMaxWidth()
                    ) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(35.dp)
                                .clickable {
                                    it.book_mark = !it.book_mark
                                    bookMarkState = it.book_mark
                                    onBookMarkClick(it)
                                },
                            imageVector = if (bookMarkState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}