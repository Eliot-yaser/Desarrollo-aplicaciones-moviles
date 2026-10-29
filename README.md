# MpazPro1

## Sobre la aplicación

MpazPro1 es una aplicación móvil desarrollada para apoyar la organización de información educativa. La aplicación cuenta con distintas vistas según el tipo de usuario, como estudiante, docente y UTP, para que cada uno pueda acceder a las opciones que le corresponden.

El proyecto fue realizado con Kotlin y Jetpack Compose en Android Studio.

## Integrantes

- [ELIOT YASER ARMIJO RAMIREZ ]
- [ISAAC ALEJANDRO ARIAS MELLADO]

## ¿Qué se puede hacer en la aplicación?

- Iniciar sesión y registrar usuarios.
- Acceder a las pantallas correspondientes a los roles de estudiante, docente y UTP.
- Navegar entre las distintas secciones de la aplicación.
- Consultar y gestionar información relacionada con asignaturas, objetivos de aprendizaje y recursos educativos, según el rol.
- Guardar información localmente mediante Room, que utiliza SQLite como base de datos.
- Validar los datos ingresados en los formularios de inicio de sesión y registro.

Los formularios de las demás funciones que aún no estén conectadas a una base de datos pueden mostrar el mensaje «Para una futura actualización», de acuerdo con las indicaciones de la evaluación.

## Tecnologías utilizadas

- Kotlin
- Android Studio
- Jetpack Compose y Material 3
- Navigation Compose
- ViewModel y StateFlow
- Room / SQLite
- Gradle

## Cómo ejecutar el proyecto

1. Descarga o clona el repositorio en tu computador.
2. Abre Android Studio y selecciona la carpeta principal del proyecto, donde se encuentra `settings.gradle.kts`.
3. Espera a que termine la sincronización de Gradle.
4. Inicia un emulador Android o conecta un dispositivo compatible.
5. Ejecuta la aplicación desde Android Studio con el botón **Run**.

También puedes compilar el proyecto desde una terminal ubicada en la carpeta principal:

- En Windows: `gradlew.bat assembleDebug`
- En macOS o Linux: `./gradlew assembleDebug`

## Organización del proyecto

El código está organizado en la carpeta `app`. Dentro de ella se encuentran las pantallas, la navegación, la lógica de la aplicación, las clases para el almacenamiento local y los recursos de Android.