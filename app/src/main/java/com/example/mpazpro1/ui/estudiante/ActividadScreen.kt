
package com.example.mpazpro1.ui.estudiante

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun ActividadScreen(
    onVolver: () -> Unit
) {
    var respuesta by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp)
    ) {
        Text(
            text = "Actividad: Las vocales",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "¿Cuál de estas letras es una vocal?",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        listOf("M", "A", "P").forEach { letra ->
            Row {
                RadioButton(
                    selected = respuesta == letra,
                    onClick = {
                        respuesta = letra
                        resultado = null
                    }
                )
                Text(
                    text = letra,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                resultado = if (respuesta == "A") {
                    "¡Muy bien! La letra A es una vocal."
                } else {
                    "Inténtalo nuevamente."
                }
            },
            enabled = respuesta.isNotEmpty()
        ) {
            Text("Comprobar respuesta")
        }

        resultado?.let {
            Spacer(Modifier.height(16.dp))
            Text(it)
        }

        Spacer(Modifier.height(24.dp))

        OutlinedButton(onClick = onVolver) {
            Text("Volver")
        }
    }
}
