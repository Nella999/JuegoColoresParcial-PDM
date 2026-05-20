package com.example.juegocoloresparcial.viewmodel

import androidx.compose.runtime.mutableStateListOf//Guardar datos
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow//Estados mutables
import kotlinx.coroutines.flow.StateFlow //Visualizar datos
import kotlin.random.Random//aleatorio

class GameViewModel : ViewModel() {

    val colors = listOf(
        "Rojo",
        "Verde",
        "Azul",
        "Amarillo",
        "Morado",
        "Negro"
    )

    //Guardar el color actual y despues guarda los cambios
    private val _currentColor = MutableStateFlow(colors.random())
    val currentColor: StateFlow<String> = _currentColor

    //Guarda el puntaje y luego meustra
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    //temporizador que empieza en 30seg
    private val _timeLeft = MutableStateFlow(30)
    val timeLeft: StateFlow<Int> = _timeLeft

    //Guarda mensajes "Correccto" e "Incorrecto"
    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    //Historial de partidas
    val history = mutableStateListOf<Int>()

    //Recibe la respuesta del usuario y despues la compara
    fun checkAnswer(answer: String) {

        if(answer == _currentColor.value){
            _score.value++ //aumenta el score
            _message.value = "Correcto"
        } else {
            _message.value = "Incorrecto"
        }
        _currentColor.value = colors.random() //Cambiar el color de manera random
    }

    // Aumenta la dificultad restando tiempo
    fun decreaseTime(){
        _timeLeft.value--
    }

    //Reiniciar la partida
    fun resetGame(){

        history.add(_score.value) //guarda el puntaje antes de reiniciar
        _score.value = 0
        _timeLeft.value = 30
        _currentColor.value = colors.random()
        _message.value = ""
    }
}