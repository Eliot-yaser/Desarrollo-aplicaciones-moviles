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
import com.example.mpazpro1.ui.estudiante.AsignaturaScreen
import com.example.mpazpro1.ui.estudiante.HomeEstudianteScreen
import com.example.mpazpro1.ui.login.LoginScreen
import com.example.mpazpro1.ui.login.LoginViewModel
import com.example.mpazpro1.ui.registro.RegistroScreen
import com.example.mpazpro1.ui.registro.RegistroViewModel
import com.example.mpazpro1.ui.utp.HomeUtpScreen

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
                onCerrarSesion = { cerrarSesion() }
            )
        }

        composable(Rutas.HOME_UTP) {
            HomeUtpScreen(
                nombre = usuario?.nombre.primerNombre(),
                onCerrarSesion = { cerrarSesion() }
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
                onVolver = { navController.popBackStack() }
            )
        }
    }
}