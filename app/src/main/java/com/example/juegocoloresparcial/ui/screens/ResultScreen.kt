package com.example.juegocoloresparcial.ui.screens

import android.content.Context //guarda datos
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items //listas
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.juegocoloresparcial.viewmodel.GameViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ResultScreen(
    navController: NavController,//navegación
    viewModel: GameViewModel
) {

    val context = LocalContext.current
    val prefs = context.getSharedPreferences("game", Context.MODE_PRIVATE)//almacenamiento local de datos
    val score by viewModel.score.collectAsState()//puntaje actual
    val highScore = prefs.getInt("highscore", 0) // buscar el puntaje más alto
    if(score > highScore){
        prefs.edit().putInt("highscore", score).apply() //Actualiza el puntaje alto si el puntaje actual es mayor
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Terminó el juego :(")
        Spacer(modifier = Modifier.height(20.dp))
        Text("Puntaje: $score") //Mostrar puntaje
        Text("Récord: ${maxOf(score, highScore)}")//Mostrar el puntaje más alto
        Spacer(modifier = Modifier.height(20.dp))
        Text("Historial")

        LazyColumn(
            modifier = Modifier.height(200.dp)
        ) {
            items(viewModel.history) {//mostrar historial
                Text("Puntaje: $it")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.resetGame() //Reinicia el puntaje, tiempo y color
                navController.navigate("game")
            }
        ) {
            Text("Jugar otra vez")
        }
    }
}
