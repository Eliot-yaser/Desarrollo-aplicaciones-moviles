package com.example.mpazpro1.ui.estudiante

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Datos ficticios del progreso del estudiante
private data class AvanceAsignaturaDemo(
    val nombre: String,
    val objetivo1: String,
    val progresoObjetivo1: Int,
    val objetivo2: String,
    val progresoObjetivo2: Int,
    val desafiosCompletados: Int,
    val totalDesafios: Int
) {
    val progresoGeneral: Int
        get() = (progresoObjetivo1 + progresoObjetivo2) / 2
}

@Composable
fun MiAvanceScreen(
    onVolver: () -> Unit
) {

    // Progreso de ejemplo para las cuatro asignaturas
    val avances = remember {
        listOf(
            AvanceAsignaturaDemo(
                nombre = "Lenguaje y Comunicación",
                objetivo1 = "Reconocer vocales",
                progresoObjetivo1 = 80,
                objetivo2 = "Identificar vocales en palabras",
                progresoObjetivo2 = 60,
                desafiosCompletados = 1,
                totalDesafios = 2
            ),

            AvanceAsignaturaDemo(
                nombre = "Matemática",
                objetivo1 = "Reconocer números del 1 al 10",
                progresoObjetivo1 = 75,
                objetivo2 = "Resolver sumas sencillas",
                progresoObjetivo2 = 55,
                desafiosCompletados = 1,
                totalDesafios = 2
            ),

            AvanceAsignaturaDemo(
                nombre = "Ciencias Naturales",
                objetivo1 = "Reconocer seres vivos",
                progresoObjetivo1 = 70,
                objetivo2 = "Cuidar la naturaleza",
                progresoObjetivo2 = 50,
                desafiosCompletados = 1,
                totalDesafios = 2
            ),

            AvanceAsignaturaDemo(
                nombre = "Historia y Geografía",
                objetivo1 = "Reconocer lugares de la comunidad",
                progresoObjetivo1 = 65,
                objetivo2 = "Comprender normas de convivencia",
                progresoObjetivo2 = 45,
                desafiosCompletados = 1,
                totalDesafios = 2
            )
        )
    }

    // Indicadores generales de demostración
    val progresoTotal = avances.sumOf {
        it.progresoGeneral
    } / avances.size

    val desafiosCompletados = avances.sumOf {
        it.desafiosCompletados
    }

    val totalDesafios = avances.sumOf {
        it.totalDesafios
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Botón de regreso
        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Volver al panel Estudiante")
        }

        Text(
            text = "Mi avance",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "¡Descubre cuánto has aprendido!",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Datos de demostración. " +
                    "Los porcentajes no representan resultados reales.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider()

        // SECCIÓN 1: PROGRESO GENERAL

        Text(
            text = "Mi progreso general",
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                Text(
                    text = "$progresoTotal%",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                LinearProgressIndicator(
                    progress = {
                        progresoTotal / 100f
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Asignaturas: ${avances.size}"
                )

                Text(
                    text = "Desafíos completados: " +
                            "$desafiosCompletados de $totalDesafios"
                )

                Text(
                    text = "¡Sigue practicando y aprendiendo!",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        HorizontalDivider()

        // SECCIÓN 2: PROGRESO POR ASIGNATURA

        Text(
            text = "Avance por asignatura",
            style = MaterialTheme.typography.titleMedium
        )

        avances.forEach { asignatura ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    Text(
                        text = asignatura.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Unidad 1 · Aprendizajes de ejemplo",
                        style = MaterialTheme.typography.bodySmall
                    )

                    // Progreso de la asignatura
                    Text(
                        text = "Avance: ${asignatura.progresoGeneral}%",
                        style = MaterialTheme.typography.titleSmall
                    )

                    LinearProgressIndicator(
                        progress = {
                            asignatura.progresoGeneral / 100f
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    HorizontalDivider()

                    Text(
                        text = "Objetivos de aprendizaje",
                        style = MaterialTheme.typography.titleSmall
                    )

                    // Primer objetivo
                    BarraAvanceEstudiante(
                        titulo = asignatura.objetivo1,
                        porcentaje = asignatura.progresoObjetivo1
                    )

                    // Segundo objetivo
                    BarraAvanceEstudiante(
                        titulo = asignatura.objetivo2,
                        porcentaje = asignatura.progresoObjetivo2
                    )

                    HorizontalDivider()

                    Text(
                        text = "Mis desafíos",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Text(
                        text = "✓ Completados: " +
                                "${asignatura.desafiosCompletados}"
                    )

                    Text(
                        text = "◷ Pendientes: " +
                                "${asignatura.totalDesafios - asignatura.desafiosCompletados}"
                    )
                }
            }
        }

        HorizontalDivider()

        // SECCIÓN 3: MENSAJE FINAL

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = "¡Tú puedes!",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Cada actividad es una oportunidad " +
                            "para aprender algo nuevo."
                )

                Text(
                    text = "Continúa explorando tus asignaturas " +
                            "y practicando con los desafíos."
                )
            }
        }

        Text(
            text = "Esta pantalla utiliza información ficticia. " +
                    "El progreso real se incorporará en una futura versión.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}

// Barra de progreso reutilizable para los OA
@Composable
private fun BarraAvanceEstudiante(
    titulo: String,
    porcentaje: Int
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "$porcentaje% completado",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        LinearProgressIndicator(
            progress = {
                porcentaje / 100f
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
