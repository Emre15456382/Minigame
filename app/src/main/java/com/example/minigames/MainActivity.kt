package com.example.minigames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

enum class Screen { MENU, MEMORY, TICTACTOE, GUESS }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var screen by rememberSaveable { mutableStateOf(Screen.MENU) }
                BackHandler(enabled = screen != Screen.MENU) { screen = Screen.MENU }
                val back = { screen = Screen.MENU }
                when (screen) {
                    Screen.MENU -> MenuScreen { screen = it }
                    Screen.MEMORY -> MemoryGame(back)
                    Screen.TICTACTOE -> TicTacToeGame(back)
                    Screen.GUESS -> GuessGame(back)
                }
            }
        }
    }
}
