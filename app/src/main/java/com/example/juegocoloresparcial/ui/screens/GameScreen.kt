package com.example.juegocoloresparcial.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController //Navegación de pantallas
import kotlinx.coroutines.delay
import com.example.juegocoloresparcial.viewmodel.GameViewModel
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.juegocoloresparcial.R
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel
) {

    val currentColor by viewModel.currentColor.collectAsState()//Actualización automática
    val score by viewModel.score.collectAsState() //puntaje
    val timeLeft by viewModel.timeLeft.collectAsState()//tiempo
    val message by viewModel.message.collectAsState()

    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000) //esperar 1 sec
            viewModel.decreaseTime()//reduce el tiempo
        }

        if (timeLeft == 0) { //termina el juego
            navController.navigate("result") { // muestra la pantalla resultado
                launchSingleTop = true //abre 1 ventana a la vez
                popUpTo("game") {
                    inclusive = true
                }
            }
        }
    }

    Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
    ) {

        Spacer(modifier = Modifier.height(20.dp))
        Text("Tiempo: $timeLeft")
        Text("Puntaje: $score")
        Spacer(modifier = Modifier.height(30.dp))

        val animatedSize by animateDpAsState( // para animar el tamaño del botón
            targetValue = 300.dp, label = "" //tamaño final
        )
        Image(
            painter = painterResource(id = getImage(currentColor)), //obtener imagenes
            contentDescription = currentColor,
            modifier = Modifier.size(animatedSize)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(currentColor)
        Spacer(modifier = Modifier.height(20.dp))

        viewModel.colors.forEach { color -> //recorre la lista para crear un botón por cada color
            Button(
                onClick = {
                    viewModel.checkAnswer(color)
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = getRandomButtonColor(color)
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ){
                Text(color)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(message)//"Correcto" o "Incorrecto"
    }
}

fun getImage(color: String): Int {
    return when(color){
        "Rojo" -> R.drawable.rojos
        "Verde" -> R.drawable.verde
        "Azul" -> R.drawable.azul
        "Amarillo" -> R.drawable.amarillo
        "Morado" -> R.drawable.morado
        else -> R.drawable.negro
    }
}
fun getRandomButtonColor(colorName: String): Color {

    return when (colorName){

        "Rojo" -> Color.Green
        "Verde" -> Color.Blue
        "Azul" -> Color.Yellow
        "Amarillo" -> Color.Magenta
        "Morado" -> Color.Black
        else -> Color.Red
    }
}

