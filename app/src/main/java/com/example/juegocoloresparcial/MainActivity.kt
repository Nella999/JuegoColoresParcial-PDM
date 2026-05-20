package com.example.juegocoloresparcial

import android.os.Bundle//guardar información de manera temporal
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.juegocoloresparcial.navigation.AppNavigation
import com.example.juegocoloresparcial.ui.theme.JuegoColoresParcialTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent { //lo que se muestra en pantalla
            JuegoColoresParcialTheme {
                AppNavigation()
            }
        }
    }
}