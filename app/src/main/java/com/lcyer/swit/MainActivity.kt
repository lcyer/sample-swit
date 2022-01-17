package com.lcyer.swit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lcyer.swit.ui.bookmark.BookMarkScreen
import com.lcyer.swit.ui.user.UserScreen
import com.lcyer.swit.ui.theme.SwitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwitTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val screens = listOf(
                        Screen.User,
                        Screen.BookMark
                    )
                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
                                val navBackStackEnty by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEnty?.destination
                                screens.forEach {
                                    BottomNavigationItem(
                                        label = {
                                            Text(
                                                text = it.title
                                            )
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = it.icon,
                                                contentDescription = null
                                            )
                                        },
                                        selected = currentDestination?.hierarchy?.any { it.route == it.route } == true,
                                        onClick = {
                                            navController.navigate(it.route) {
                                                popUpTo(
                                                    id = navController.graph.findStartDestination().id
                                                ) {
                                                    saveState = true
                                                }

                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = Screen.User.route
                        ) {
                            composable(Screen.User.route) {
                                UserScreen(
                                    navController = navController
                                )
                            }
                            composable(Screen.BookMark.route) {
                                BookMarkScreen(
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}