package com.example.mpazpro1.ui.recursos

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mpazpro1.data.local.EstadoRecurso
import com.example.mpazpro1.data.local.Recurso
import com.example.mpazpro1.ui.componentes.asignaturasUi

@Composable
fun RecursosScreen(
    viewModel: RecursosViewModel,
    onVolver: () -> Unit
) {
    val publicados by viewModel.publicados.collectAsState()
    val misRecursos by viewModel.misRecursos.collectAsState()
    val pendientes by viewModel.pendientes.collectAsState()
    val procesando by viewModel.procesando.collectAsState()
    val mensaje by viewModel.mensaje.collectAsState()
    val autores by viewModel.autores.collectAsState()

    var seccion by rememberSaveable { mutableStateOf("Publicados") }
    var creando by rememberSaveable { mutableStateOf(false) }
    var recursoRevision by remember { mutableStateOf<Recurso?>(null) }

    var titulo by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var asignaturaId by rememberSaveable { mutableStateOf(1) }
    var observacion by rememberSaveable { mutableStateOf("") }

    val snackbar = remember { SnackbarHostState() }

    LaunchedEffect(mensaje) {
        mensaje?.let {
            snackbar.showSnackbar(it)
            viewModel.limpiarMensaje()
        }
    }

    fun volver() {
        when {
            creando -> creando = false
            recursoRevision != null -> recursoRevision = null
            else -> onVolver()
        }
    }

    BackHandler(enabled = creando || recursoRevision != null) {
        if (!procesando) volver()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbar) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            OutlinedButton(
                onClick = { volver() },
                enabled = !procesando
            ) {
                Text("← Volver")
            }

            Text(
                text = when {
                    creando -> "Agregar recurso"
                    recursoRevision != null -> "Revisar recurso"
                    else -> "Recursos educativos"
                },
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Cuenta: ${viewModel.usuario.nombre}",
                style = MaterialTheme.typography.bodyMedium
            )

            HorizontalDivider()

            if (creando) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título del recurso") },
                    enabled = !procesando,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción o contenido") },
                    enabled = !procesando,
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    "Asignatura",
                    style = MaterialTheme.typography.titleMedium
                )

                asignaturasUi.forEach { asignatura ->
                    FilterChip(
                        selected = asignaturaId == asignatura.id,
                        onClick = { asignaturaId = asignatura.id },
                        enabled = !procesando,
                        label = { Text(asignatura.nombre) }
                    )
                }

                Text(
                    if (viewModel.esUtp) {
                        "El recurso quedará publicado directamente."
                    } else {
                        "UTP debe revisar el recurso antes de publicarlo."
                    }
                )

                Button(
                    onClick = {
                        viewModel.crearRecurso(
                            titulo = titulo,
                            descripcion = descripcion,
                            asignaturaId = asignaturaId,
                            onCreado = {
                                creando = false
                                seccion = if (viewModel.esUtp) {
                                    "Publicados"
                                } else {
                                    "Mis recursos"
                                }
                            }
                        )
                    },
                    enabled = !procesando,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        when {
                            procesando -> "Guardando..."
                            viewModel.esUtp -> "Publicar recurso"
                            else -> "Enviar a revisión"
                        }
                    )
                }
            } else if (recursoRevision != null) {
                val recurso = recursoRevision!!

                Text(
                    recurso.titulo,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(recurso.descripcion)

                Text(
                    "Asignatura: " + (
                            asignaturasUi
                                .firstOrNull { it.id == recurso.asignaturaId }
                                ?.nombre ?: "Sin asignatura"
                            )
                )

                Text(
                    "Enviado por: ${autores[recurso.autorId] ?: "Cargando autor..."}"
                )

                OutlinedTextField(
                    value = observacion,
                    onValueChange = { observacion = it },
                    enabled = !procesando,
                    label = { Text("Observación de UTP") },
                    supportingText = {
                        Text("Obligatoria si rechazas el recurso.")
                    },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        viewModel.revisarRecurso(
                            recursoId = recurso.id,
                            aprobar = true,
                            observacion = observacion,
                            onRevisado = { recursoRevision = null }
                        )
                    },
                    enabled = !procesando,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Aprobar y publicar")
                }

                OutlinedButton(
                    onClick = {
                        viewModel.revisarRecurso(
                            recursoId = recurso.id,
                            aprobar = false,
                            observacion = observacion,
                            onRevisado = { recursoRevision = null }
                        )
                    },
                    enabled = !procesando,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Rechazar")
                }
            } else {
                Button(
                    onClick = {
                        titulo = ""
                        descripcion = ""
                        asignaturaId = 1
                        creando = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar recurso")
                }

                val secciones = if (viewModel.esUtp) {
                    listOf("Publicados", "Mis recursos", "Revisar")
                } else {
                    listOf("Publicados", "Mis recursos")
                }

                // En columna para que quepan en teléfonos pequeños.
                secciones.forEach { opcion ->
                    FilterChip(
                        selected = seccion == opcion,
                        onClick = { seccion = opcion },
                        label = {
                            Text(
                                if (opcion == "Revisar") {
                                    "Revisar (${pendientes.size})"
                                } else {
                                    opcion
                                }
                            )
                        }
                    )
                }

                val recursos = when (seccion) {
                    "Mis recursos" -> misRecursos
                    "Revisar" -> pendientes
                    else -> publicados
                }

                if (recursos.isEmpty()) {
                    Text(
                        when (seccion) {
                            "Mis recursos" -> "Todavía no has creado recursos."
                            "Revisar" -> "No hay recursos pendientes de revisión."
                            else -> "Todavía no hay recursos publicados."
                        }
                    )
                }

                recursos.forEach { recurso ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                recurso.titulo,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(recurso.descripcion)

                            Text(
                                asignaturasUi
                                    .firstOrNull {
                                        it.id == recurso.asignaturaId
                                    }
                                    ?.nombre ?: "Sin asignatura"
                            )

                            Text(
                                "Creado por: ${
                                    autores[recurso.autorId] ?: "Cargando autor..."
                                }"
                            )

                            val estadoVisible = when (recurso.estado) {
                                EstadoRecurso.PENDIENTE -> "Pendiente"
                                EstadoRecurso.RECHAZADO -> "Rechazado"
                                else -> {
                                    if (
                                        seccion == "Mis recursos" &&
                                        !viewModel.esUtp
                                    ) {
                                        "Aprobado"
                                    } else {
                                        "Publicado"
                                    }
                                }
                            }

                            Text(
                                "Estado: $estadoVisible",
                                color = MaterialTheme.colorScheme.primary
                            )

                            if (recurso.observacionUtp.isNotBlank()) {
                                Text(
                                    "Observación de UTP: " +
                                            recurso.observacionUtp
                                )
                            }

                            if (
                                viewModel.esUtp &&
                                recurso.estado == EstadoRecurso.PENDIENTE
                            ) {
                                Button(
                                    onClick = {
                                        observacion = ""
                                        recursoRevision = recurso
                                    },
                                    enabled = !procesando
                                ) {
                                    Text("Revisar recurso")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}