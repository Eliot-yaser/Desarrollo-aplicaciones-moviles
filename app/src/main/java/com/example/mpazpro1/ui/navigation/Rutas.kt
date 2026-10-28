package com.example.mpazpro1.ui.navigation

object Rutas {
    const val BIENVENIDA = "bienvenida"
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val HOME_ESTUDIANTE = "home_estudiante"
    const val HOME_DOCENTE = "home_docente"
    const val HOME_UTP = "home_utp"

    const val ASIGNAR_OBJETIVOS = "asignar_objetivos"
    const val MIS_CURSOS = "mis_cursos"
    const val INFORME_RENDIMIENTO = "informe_rendimiento"
    const val RECURSOS_EDUCATIVOS = "recursos_educativos"
    const val CONFIGURAR_PRUEBA_FINAL = "configurar_prueba_final"

    const val CONFIGURACION_CURRICULAR = "configuracion_curricular"


    const val ASIGNATURA = "asignatura"
    const val ACTIVIDAD = "actividad"
    const val ACTIVIDAD_ARG = "asignaturaId"
    const val ASIGNATURA_ARG = "asignaturaId"
    fun asignatura(id: Int) = "$ASIGNATURA/$id"
    fun actividad(id: Int) = "$ACTIVIDAD/$id"
}