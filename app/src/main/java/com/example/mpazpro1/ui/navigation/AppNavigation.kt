package com.example.mpazpro1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mpazpro1.domain.Roles
import com.example.mpazpro1.ui.bienvenida.BienvenidaScreen
import com.example.mpazpro1.ui.componentes.asignaturasUi
import com.example.mpazpro1.ui.docente.HomeDocenteScreen
import com.example.mpazpro1.ui.docente.AsignarObjetivosScreen
import com.example.mpazpro1.ui.docente.MisCursosScreen
import com.example.mpazpro1.ui.docente.InformeRendimientoScreen
import com.example.mpazpro1.ui.docente.RecursosEducativosScreen
import com.example.mpazpro1.ui.docente.ConfigurarPruebaFinalScreen
import com.example.mpazpro1.ui.estudiante.AsignaturaScreen
import com.example.mpazpro1.ui.estudiante.ActividadScreen
import com.example.mpazpro1.ui.estudiante.HomeEstudianteScreen
import com.example.mpazpro1.ui.login.LoginScreen
import com.example.mpazpro1.ui.login.LoginViewModel
import com.example.mpazpro1.ui.registro.RegistroScreen
import com.example.mpazpro1.ui.registro.RegistroViewModel
import com.example.mpazpro1.ui.utp.HomeUtpScreen
import com.example.mpazpro1.ui.utp.ConfiguracionCurricularScreen
import com.example.mpazpro1.ui.utp.RevisionContenidosScreen
import com.example.mpazpro1.ui.utp.EditorContenidosScreen

private fun String?.primerNombre(): String = this?.trim()?.substringBefore(" ").orEmpty()

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val factory = remember { AppViewModelProvider.factory(context) }

    // Este ViewModel vive mientras la app esté abierta y guarda quién inició sesión
    val loginViewModel: LoginViewModel = viewModel(factory = factory)
    val usuario by loginViewModel.usuarioLogueado.collectAsState()

    val navController = rememberNavController()

    fun cerrarSesion() {
        loginViewModel.cerrarSesion()
        navController.navigate(Rutas.BIENVENIDA) {
            popUpTo(navController.graph.id) { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = Rutas.BIENVENIDA) {

        composable(Rutas.BIENVENIDA) {
            BienvenidaScreen(
                onIniciarSesion = { navController.navigate(Rutas.LOGIN) },
                onCrearCuenta = { navController.navigate(Rutas.REGISTRO) }
            )
        }

        composable(Rutas.LOGIN) {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = { u ->
                    // Cada tipo de usuario llega a su propia pantalla de inicio
                    val destino = when (u.rol.uppercase()) {
                        Roles.DOCENTE -> Rutas.HOME_DOCENTE
                        Roles.UTP -> Rutas.HOME_UTP
                        else -> Rutas.HOME_ESTUDIANTE
                    }
                    navController.navigate(destino) {
                        popUpTo(Rutas.BIENVENIDA) { inclusive = true }
                    }
                },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Rutas.REGISTRO) {
            val registroViewModel: RegistroViewModel = viewModel(factory = factory)
            RegistroScreen(
                viewModel = registroViewModel,
                onVolver = { navController.popBackStack() },
                onIrALogin = {
                    navController.navigate(Rutas.LOGIN) {
                        popUpTo(Rutas.BIENVENIDA)
                    }
                }
            )
        }

        composable(Rutas.HOME_ESTUDIANTE) {
            HomeEstudianteScreen(
                nombre = usuario?.nombre.primerNombre(),
                onAsignatura = { id -> navController.navigate(Rutas.asignatura(id)) },
                onCerrarSesion = { cerrarSesion() }
            )
        }

        composable(Rutas.HOME_DOCENTE) {
            HomeDocenteScreen(
                nombre = usuario?.nombre.primerNombre(),
                onCerrarSesion = { cerrarSesion() },
                onAsignarObjetivos = {
                    navController.navigate(Rutas.ASIGNAR_OBJETIVOS)
                },
                onMisCursos = {
                    navController.navigate(Rutas.MIS_CURSOS)
                },
                onInformeRendimiento = {
                    navController.navigate(Rutas.INFORME_RENDIMIENTO)
                },
                onRecursosEducativos = {
                    navController.navigate(Rutas.RECURSOS_EDUCATIVOS)
                }
            )
        }

        composable(Rutas.ASIGNAR_OBJETIVOS) {
            AsignarObjetivosScreen(
                onVolver = {
                    navController.popBackStack()
                },
                onConfigurarPruebaFinal = {
                    navController.navigate(Rutas.CONFIGURAR_PRUEBA_FINAL)
                }
            )
        }

        composable(Rutas.MIS_CURSOS) {
            MisCursosScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.INFORME_RENDIMIENTO) {
            InformeRendimientoScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.RECURSOS_EDUCATIVOS) {
            RecursosEducativosScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.CONFIGURAR_PRUEBA_FINAL) {
            ConfigurarPruebaFinalScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.HOME_UTP) {
            HomeUtpScreen(
                nombre = usuario?.nombre.primerNombre(),
                onCerrarSesion = { cerrarSesion() },
                onConfiguracionCurricular = {
                    navController.navigate(Rutas.CONFIGURACION_CURRICULAR)
                },
                onRevisionContenidos = {
                    navController.navigate(Rutas.REVISION_CONTENIDOS)
                }
            )
        }

        composable(Rutas.CONFIGURACION_CURRICULAR) {
            ConfiguracionCurricularScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.REVISION_CONTENIDOS) {
            RevisionContenidosScreen(
                onVolver = {
                    navController.popBackStack()
                },
                onCrearContenido = {
                    navController.navigate(Rutas.EDITOR_CONTENIDOS)
                }
            )
        }

        composable(Rutas.EDITOR_CONTENIDOS) {
            EditorContenidosScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${Rutas.ASIGNATURA}/{${Rutas.ASIGNATURA_ARG}}",
            arguments = listOf(navArgument(Rutas.ASIGNATURA_ARG) { type = NavType.IntType })
        ) { entrada ->
            val id = entrada.arguments?.getInt(Rutas.ASIGNATURA_ARG) ?: 1
            val asignatura = asignaturasUi.firstOrNull { it.id == id } ?: asignaturasUi.first()
            AsignaturaScreen(
                asignatura = asignatura,
                onVolver = { navController.popBackStack() },
                onComenzarActividad = {
                    navController.navigate(Rutas.actividad(id))
                }
            )
        }

        composable(
            route = "${Rutas.ACTIVIDAD}/{${Rutas.ACTIVIDAD_ARG}}",
            arguments = listOf(
                navArgument(Rutas.ACTIVIDAD_ARG) {
                    type = NavType.IntType
                }
            )
        ) { entrada ->
            ActividadScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

    }
}