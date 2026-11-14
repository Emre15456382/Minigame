package com.example.minigames

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(onSelect: (Screen) -> Unit) {
    Column(
        Modifier.fillMaxSize().systemBarsPadding().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🎮 Minigames", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(32.dp))
        MenuButton("❌⭕ XOX") { onSelect(Screen.TICTACTOE) }
        MenuButton("🃏 Hafıza Kartları") { onSelect(Screen.MEMORY) }
        MenuButton("🔢 Sayı Tahmin") { onSelect(Screen.GUESS) }
    }
}

@Composable
private fun MenuButton(text: String, onClick: () -> Unit) {
    Button(onClick, Modifier.fillMaxWidth().padding(vertical = 8.dp).height(56.dp)) { Text(text) }
}

@Composable
fun GameHeader(title: String, onBack: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        TextButton(onBack) { Text("← Geri") }
        Spacer(Modifier.weight(1f))
        Text(title, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.weight(1f))
        Spacer(Modifier.width(64.dp))
    }
    Spacer(Modifier.height(16.dp))
}
