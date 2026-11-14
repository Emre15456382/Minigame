package com.example.minigames

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class MemoryCard(
    val id: Int,
    val emoji: String,
    val faceUp: Boolean = false,
    val matched: Boolean = false
)

private val emojis = listOf("🐶", "🐱", "🦊", "🐼", "🐸", "🦁", "🐵", "🐙")
private fun newDeck() = (emojis + emojis).shuffled().mapIndexed { i, e -> MemoryCard(i, e) }

@Composable
fun MemoryGame(onBack: () -> Unit) {
    var cards by remember { mutableStateOf(newDeck()) }
    var selected by remember { mutableStateOf(listOf<Int>()) }
    var moves by remember { mutableIntStateOf(0) }
    val won = cards.all { it.matched }

    LaunchedEffect(selected) {
        if (selected.size == 2) {
            delay(700)
            val isMatch = cards[selected[0]].emoji == cards[selected[1]].emoji
            cards = cards.map { c ->
                when {
                    c.id !in selected -> c
                    isMatch -> c.copy(matched = true)
                    else -> c.copy(faceUp = false)
                }
            }
            selected = emptyList()
        }
    }

    Column(
        Modifier.fillMaxSize().systemBarsPadding().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameHeader("Hafıza Kartları", onBack)
        Text(
            if (won) "Bitti! $moves hamlede 🎉" else "Hamle: $moves",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            GridCells.Fixed(4),
            Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cards, key = { it.id }) { card ->
                val show = card.faceUp || card.matched
                Box(
                    Modifier.aspectRatio(1f)
                        .background(
                            if (card.matched) MaterialTheme.colorSche
