package com.example.pokedir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedir.pokemondetail.PokemonDetailScreen
import com.example.pokedir.pokemonlist.PokemonListScreen
import com.example.pokedir.ui.theme.PokeDirTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDirTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = "pokemon_list_screen"
                ) {
                    composable("pokemon_list_screen") {
                        PokemonListScreen(navController = navController)
                    }
                    composable("pokemon_detail_screen/{dominantColor}/{pokemonName}",
                        arguments = listOf(navArgument("dominantColor") {
                            type = NavType.IntType
                        }, navArgument("pokemonName") {
                            type = NavType.StringType
                        })) {
                        val dominantColor = remember{
                           it.arguments?.getInt("dominantColor")?.let { Color(it) } ?: Color.White
                        }
                        val pokemonMame = remember {
                            it.arguments?.getString("pokemonName")
                        }
                        PokemonDetailScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonMame?.toLowerCase(Locale.ROOT) ?: "",
                            navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokeDirTheme {
        Greeting("Android")
    }
}