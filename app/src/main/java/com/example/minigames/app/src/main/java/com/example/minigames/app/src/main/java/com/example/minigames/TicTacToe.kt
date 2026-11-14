package com.example.minigames

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val lines = listOf(
    listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
    listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
    listOf(0, 4, 8), listOf(2, 4, 6)
)

private fun winnerOf(b: List<String>): String? =
    lines.firstOrNull { (a, c, d) -> b[a].isNotEmpty() && b[a] == b[c] && b[a] == b[d] }
        ?.let { b[it[0]] }

@Composable
fun TicTacToeGame(onBack: () -> Unit) {
    var board by remember { mutableStateOf(List(9) { "" }) }
    var turn by remember { mutableStateOf("X") }
    val winner = winnerOf(board)
    val draw = winner == null && board.all { it.isNotEmpty() }

    Column(
        Modifier.fillMaxSize().systemBarsPadding().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameHeader("XOX", onBack)
        Text(
            when {
                winner != null -> "Kazanan: $winner 🎉"
                draw -> "Berabere"
                else -> "Sıra: $turn"
            },
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(24.dp))
        for (r in 0..2) {
            Row {
                for (c in 0..2) {
                    val i = r * 3 + c
                    Box(
                        Modifier.padding(4.dp).size(96.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                            .clickable(enabled = board[i].isEmpty() && winner == null) {
                                board = board.toMutableList().also { it[i] = turn }
                                turn = if (turn == "X") "O" else "X"
                            },
                        contentAlignment = Alignment.Center
                    ) { Text(board[i], fontSize = 40.sp) }
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        Button({ board = List(9) { "" }; turn = "X" }) { Text("Yeniden başla") }
    }
}
