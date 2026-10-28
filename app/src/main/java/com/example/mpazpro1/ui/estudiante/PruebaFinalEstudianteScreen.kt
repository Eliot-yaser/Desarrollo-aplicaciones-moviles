package com.example.mpazpro1.ui.estudiante

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.mpazpro1.ui.componentes.asignaturasUi
import kotlinx.coroutines.launch

@Composable
fun PruebaFinalEstudianteScreen(asignaturaId: Int, onVolver: () -> Unit) {
    val contenido = remember(asignaturaId) { aprendizajeDemo(asignaturaId) }
    val asignatura = asignaturasUi.first { it.id == asignaturaId }
    var etapa by rememberSaveable(asignaturaId) { mutableStateOf(0) }
    var respuestas by rememberSaveable(asignaturaId) { mutableStateOf(IntArray(contenido.preguntas.size) { -1 }) }
    var intentos by rememberSaveable(asignaturaId) { mutableStateOf(0) }
    var confirmarSalida by remember { mutableStateOf(false) }
    var verBloqueo by remember { mutableStateOf(false) }
    val maxIntentos = 2
    val mensajes = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scroll = rememberScrollState()
    LaunchedEffect(etapa) { scroll.scrollTo(0) }
    fun salir() { if (etapa == 1) confirmarSalida = true else onVolver() }
    BackHandler(enabled = etapa == 1) { confirmarSalida = true }

    Scaffold(snackbarHost = { SnackbarHost(mensajes) }) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).verticalScroll(scroll).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(onClick = { salir() }) { Text("Volver a la asignatura") }
            Text("Prueba final", style = MaterialTheme.typography.headlineMedium)
            Text(asignatura.nombre, style = MaterialTheme.typography.titleLarge)
            Text(contenido.unidad)
            Text("Demostración: las respuestas y los intentos no se guardan en la base de datos ni actualizan Mi avance.", style = MaterialTheme.typography.bodySmall)
            when (etapa) {
                0 -> {
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text("Disponible · Ejemplo de desafíos requeridos completados", style = MaterialTheme.typography.titleMedium)
                            Text("Fecha de habilitación de ejemplo: 28/10/2026")
                            Text("Intentos de esta demostración: $intentos de $maxIntentos")
                            Text("Objetivo: ${contenido.objetivo}")
                        }
                    }
                    Text("Lee cada pregunta y elige una respuesta. Son ${contenido.preguntas.size} preguntas. Puedes revisar tus elecciones antes de terminar.")
                    Button(onClick = { intentos++; etapa = 1 }, enabled = intentos < maxIntentos, modifier = Modifier.fillMaxWidth()) {
                        Text("Comenzar prueba")
                    }
                    OutlinedButton(onClick = { verBloqueo = true }, modifier = Modifier.fillMaxWidth()) {
                        Text("¿Cuándo estará bloqueada?")
                    }
                }
                1 -> {
                    Text("Intento $intentos de $maxIntentos · ${respuestas.count { it >= 0 }} de ${respuestas.size} respondidas")
                    contenido.preguntas.forEachIndexed { indice, pregunta ->
                        Card(Modifier.fillMaxWidth()) {
                            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("${indice + 1}. ${pregunta.texto}", style = MaterialTheme.typography.titleMedium)
                                pregunta.alternativas.forEachIndexed { opcion, texto ->
                                    Row(
                                        Modifier.fillMaxWidth().selectable(
                                            selected = respuestas[indice] == opcion,
                                            role = Role.RadioButton,
                                            onClick = { respuestas = respuestas.copyOf().also { it[indice] = opcion } }
                                        ).padding(vertical = 8.dp),
                                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                                    ) {
                                        RadioButton(selected = respuestas[indice] == opcion, onClick = null)
                                        Spacer(Modifier.width(12.dp))
                                        Text(texto, style = MaterialTheme.typography.bodyLarge)
                                    }
                                }
                            }
                        }
                    }
                    Button(onClick = { etapa = 2 }, enabled = respuestas.all { it >= 0 }, modifier = Modifier.fillMaxWidth()) {
                        Text("Terminar y ver resultados")
                    }
                    if (respuestas.any { it < 0 }) Text("Responde todas las preguntas para ver tus resultados.")
                }
                2 -> {
                    val aciertos = contenido.preguntas.indices.count { respuestas[it] == contenido.preguntas[it].correcta }
                    Text("¡Terminaste!", style = MaterialTheme.typography.headlineSmall)
                    Text("Correctas: $aciertos · Incorrectas: ${respuestas.size - aciertos} · Total: ${respuestas.size}", style = MaterialTheme.typography.titleMedium)
                    Text(if (aciertos == respuestas.size) "¡Muy bien! Sigue aprendiendo." else "¡Buen esfuerzo! Revisa las respuestas y sigue practicando.")
                    contenido.preguntas.forEachIndexed { indice, pregunta ->
                        val correcta = respuestas[indice] == pregunta.correcta
                        Card(Modifier.fillMaxWidth()) {
                            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("${indice + 1}. ${pregunta.texto}", style = MaterialTheme.typography.titleMedium)
                                Text(if (correcta) "Respuesta correcta" else "Respuesta incorrecta")
                                Text("Elegiste: ${pregunta.alternativas[respuestas[indice]]}")
                                Text("Respuesta correcta: ${pregunta.alternativas[pregunta.correcta]}")
                                Text(pregunta.explicacion)
                            }
                        }
                    }
                    Text("Intentos de esta demostración: $intentos de $maxIntentos")
                    Button(
                        onClick = { respuestas = IntArray(contenido.preguntas.size) { -1 }; intentos++; etapa = 1 },
                        enabled = intentos < maxIntentos,
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Repetir prueba") }
                    if (intentos >= maxIntentos) Text("Prueba bloqueada: alcanzaste el límite de intentos de esta demostración.")
                    OutlinedButton(
                        onClick = { scope.launch { mensajes.showSnackbar("Para una futura actualización") } },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Guardar resultado") }
                    OutlinedButton(onClick = onVolver, modifier = Modifier.fillMaxWidth()) { Text("Volver a mis objetivos") }
                }
            }
        }
    }
    if (confirmarSalida) AlertDialog(
        onDismissRequest = { confirmarSalida = false },
        title = { Text("¿Salir de la prueba?") },
        text = { Text("Este intento no se guardará. Puedes quedarte y terminar tus respuestas.") },
        confirmButton = { TextButton(onClick = { confirmarSalida = false; onVolver() }) { Text("Salir") } },
        dismissButton = { TextButton(onClick = { confirmarSalida = false }) { Text("Seguir respondiendo") } }
    )
    if (verBloqueo) AlertDialog(
        onDismissRequest = { verBloqueo = false },
        title = { Text("Prueba bloqueada · Ejemplo") },
        text = { Text("No estará disponible antes de la fecha indicada, si faltan desafíos requeridos o al alcanzar el límite de intentos. Aquí se simulan los requisitos completados; las asignaciones reales quedan para una futura actualización.") },
        confirmButton = { TextButton(onClick = { verBloqueo = false }) { Text("Entendido") } }
    )
}
