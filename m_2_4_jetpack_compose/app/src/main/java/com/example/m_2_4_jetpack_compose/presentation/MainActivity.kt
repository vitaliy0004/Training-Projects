package com.example.m_2_4_jetpack_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.m_2_4_jetpack_compose.presentation.item.Item
import com.example.m_2_4_jetpack_compose.presentation.list.ListClass
import com.example.m_2_4_jetpack_compose.presentation.theme.JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var factory: Factory
    private val viewModel: MainViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            JetpackComposeTheme {
                NavHost(navController = navController, startDestination = "ListClass") {
                    composable("ListClass") {
                        ListClass(viewModel) {
                            navController.navigate("item")
                        }
                    }
                    composable("item") {
                        Item(viewModel)
                    }

                }

            }
        }
    }
}

