package presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import presentation.item.Item
import presentation.list.ListClass
import presentation.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    private var viewModel = MainViewModel()
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

