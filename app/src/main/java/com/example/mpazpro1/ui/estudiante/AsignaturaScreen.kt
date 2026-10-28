package com.example.mpazpro1.ui.estudiante

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mpazpro1.ui.componentes.AsignaturaUi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.OutlinedButton


// Pantalla de asignatura del estudiante
@Composable
fun AsignaturaScreen(
    asignatura: AsignaturaUi,
    onVolver: () -> Unit,
    onComenzarActividad: () -> Unit,
    onVerDesafios: () -> Unit,
    onVerContenido: () -> Unit,
    onPruebaFinal: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            // Encabezado de la asignatura
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(asignatura.color)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onVolver) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }

                Text(
                    text = asignatura.nombre,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(24.dp))

            // Título de la sección
            Text(
                text = "Mis objetivos de aprendizaje",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            // Tarjeta del objetivo de aprendizaje
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    val contenido = aprendizajeDemo(asignatura.id)
                    Text(contenido.unidad, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(12.dp))
                    Text(contenido.objetivo, style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(8.dp))
                    Text("Objetivo asignado de demostración · Datos ficticios", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.height(12.dp))
                    OutlinedButton(onClick = onVerContenido, modifier = Modifier.fillMaxWidth()) {
                        Text("Ver contenido y ejemplos")
                    }

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = onComenzarActividad,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Comenzar actividad")
                    }

                    Spacer(Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = onVerDesafios,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Mis desafíos")
                    }
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = onPruebaFinal, modifier = Modifier.fillMaxWidth()) {
                        Text("Prueba final")
                    }


                }
            }
        }
    }
}
