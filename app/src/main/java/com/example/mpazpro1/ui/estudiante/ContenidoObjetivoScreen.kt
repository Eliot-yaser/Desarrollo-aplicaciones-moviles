package com.example.mpazpro1.ui.estudiante

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mpazpro1.ui.componentes.asignaturasUi
import kotlinx.coroutines.launch

@Composable
fun ContenidoObjetivoScreen(asignaturaId: Int, onVolver: () -> Unit, onActividad: () -> Unit) {
    val contenido = remember(asignaturaId) { aprendizajeDemo(asignaturaId) }
    val asignatura = asignaturasUi.first { it.id == asignaturaId }
    val mensajes = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(snackbarHost = { SnackbarHost(mensajes) }) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(onClick = onVolver) { Text("Volver a la asignatura") }
            Text(asignatura.nombre, style = MaterialTheme.typography.headlineSmall)
            Text(contenido.unidad, style = MaterialTheme.typography.titleLarge)
            Text("Contenido de demostración · Objetivo asignado de ejemplo", style = MaterialTheme.typography.bodySmall)
            Text("¿Qué aprenderemos?", fontWeight = FontWeight.Bold)
            Text(contenido.objetivo)
            Text(contenido.explicacion, style = MaterialTheme.typography.bodyLarge)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Apoyo visual", style = MaterialTheme.typography.titleMedium)
                    Text(
                        contenido.apoyoVisual,
                        modifier = Modifier.fillMaxWidth().semantics { contentDescription = contenido.descripcionVisual },
                        fontSize = 30.sp, lineHeight = 42.sp, textAlign = TextAlign.Center
                    )
                    Text(contenido.descripcionVisual, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Text("Ejemplo paso a paso", style = MaterialTheme.typography.titleMedium)
            Text(contenido.ejemplo)
            Text("Ahora tú", style = MaterialTheme.typography.titleMedium)
            Text(contenido.instrucciones)
            OutlinedButton(
                onClick = { scope.launch { mensajes.showSnackbar("Para una futura actualización") } },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Escuchar explicación") }
            Text("Audio pendiente. Puedes leer la explicación en esta pantalla.", style = MaterialTheme.typography.bodySmall)
            Button(onClick = onActividad, modifier = Modifier.fillMaxWidth()) { Text("Practicar con una actividad") }
            Text("Material propio de demostración; no corresponde a un código OA oficial validado.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
