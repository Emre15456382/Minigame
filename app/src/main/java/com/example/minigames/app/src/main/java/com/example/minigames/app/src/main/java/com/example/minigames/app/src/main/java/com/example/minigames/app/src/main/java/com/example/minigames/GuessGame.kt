package com.example.minigames

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun GuessGame(onBack: () -> Unit) {
    var target by remember { mutableIntStateOf(Random.nextInt(1, 101)) }
    var input by remember { mutableStateOf("") }
    var attempts by remember { mutableIntStateOf(0) }
    var hint by remember { mutableStateOf("1 ile 100 arasında bir sayı tuttum") }
    var won by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().systemBarsPadding().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameHeader("Sayı Tahmin", onBack)
        Text(hint, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Text("Deneme: $attempts")
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = input,
            onValueChange = { input = it.filter(Char::isDigit).take(3) },
            label = { Text("Tahminin") },
            enabled = !won,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                val guess = input.toIntOrNull() ?: return@Button
                attempts++
                hint = when {
                    guess < target -> "⬆️ Daha büyük"
                    guess > target -> "⬇️ Daha küçük"
                    else -> { won = true; "🎉 Buldun! $attempts denemede" }
                }
                input = ""
            },
            enabled = !won && input.isNotEmpty()
        ) { Text("Tahmin et") }
        Spacer(Modifier.height(24.dp))
        OutlinedButton({
            target = Random.nextInt(1, 101); input = ""; attempts = 0; won = false
            hint = "1 ile 100 arasında bir sayı tuttum"
        }) { Text("Yeni oyun") }
    }
}
