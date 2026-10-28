package com.example.mpazpro1.ui.estudiante

data class PreguntaDemo(
    val texto: String,
    val alternativas: List<String>,
    val correcta: Int,
    val explicacion: String
)

data class AprendizajeDemo(
    val unidad: String,
    val objetivo: String,
    val explicacion: String,
    val ejemplo: String,
    val instrucciones: String,
    val apoyoVisual: String,
    val descripcionVisual: String,
    val preguntas: List<PreguntaDemo>
)

// Material propio de demostración. No representa códigos OA oficiales ni asignaciones reales.
fun aprendizajeDemo(asignaturaId: Int): AprendizajeDemo = when (asignaturaId) {
    2 -> AprendizajeDemo(
        "Unidad 1: Contamos y sumamos",
        "Contar hasta diez y resolver sumas sencillas.",
        "Contar nos ayuda a saber cuántos objetos hay. Sumar significa juntar dos grupos.",
        "Tienes 2 fichas y recibes 3 más. Cuenta todas: 1, 2, 3, 4, 5. Entonces, 2 + 3 = 5.",
        "Cuenta las fichas de cada grupo. Luego cuenta cuántas hay en total.",
        "● ●   +   ● ● ●\n2 + 3 = 5",
        "Dos fichas más tres fichas forman cinco fichas.",
        listOf(
            PreguntaDemo("¿Cuánto es 2 + 3?", listOf("4", "5", "6"), 1, "Al juntar 2 y 3 objetos tienes 5."),
            PreguntaDemo("¿Qué número sigue después del 6?", listOf("5", "8", "7"), 2, "Contamos 5, 6, 7. Después del 6 viene el 7."),
            PreguntaDemo("Hay 4 lápices y agregas 1. ¿Cuántos hay?", listOf("5", "3", "6"), 0, "Cuatro más uno es igual a cinco.")
        )
    )
    3 -> AprendizajeDemo(
        "Unidad 1: Exploramos la naturaleza",
        "Distinguir seres vivos y elementos no vivos, y reconocer acciones de cuidado.",
        "Las plantas y los animales son seres vivos: necesitan agua y otras condiciones para vivir y crecer. Una piedra no es un ser vivo.",
        "Una planta crece cuando recibe agua y luz. Una piedra no crece como una planta.",
        "Observa el dibujo. Compara la planta con la piedra y piensa cuál necesita agua para vivir.",
        "🌱    ☀    💧    🪨",
        "Una planta con luz y agua junto a una piedra; la planta es un ser vivo.",
        listOf(
            PreguntaDemo("¿Cuál es un ser vivo?", listOf("Una piedra", "Una silla", "Una planta"), 2, "La planta vive, necesita agua y crece."),
            PreguntaDemo("¿Qué necesita una planta para vivir?", listOf("Agua y luz", "Juguetes", "Zapatos"), 0, "El agua y la luz ayudan a la planta a vivir y crecer."),
            PreguntaDemo("¿Cómo cuidamos la naturaleza?", listOf("Botando basura al río", "Recogiendo la basura", "Rompiendo las plantas"), 1, "Recoger la basura ayuda a cuidar el entorno.")
        )
    )
    4 -> AprendizajeDemo(
        "Unidad 1: Mi escuela y comunidad",
        "Reconocer lugares de la comunidad y acciones de buena convivencia.",
        "En nuestra comunidad hay lugares para aprender, jugar y recibir ayuda. Las normas nos ayudan a cuidarnos y respetarnos.",
        "En la escuela esperamos nuestro turno para hablar. En la plaza compartimos los juegos y cuidamos el espacio.",
        "Observa los lugares. Piensa dónde aprendes y qué puedes hacer para convivir mejor.",
        "🏠    🏫    🌳",
        "Una casa, una escuela y un árbol que representa una plaza de la comunidad.",
        listOf(
            PreguntaDemo("¿En qué lugar aprendes junto a tu curso?", listOf("En la escuela", "En una tienda", "En un estacionamiento"), 0, "En la escuela aprendemos junto al curso y al docente."),
            PreguntaDemo("¿Qué hacemos cuando otra persona habla?", listOf("Gritamos", "La interrumpimos", "Escuchamos y esperamos"), 2, "Escuchar y esperar el turno demuestra respeto."),
            PreguntaDemo("¿Cómo cuidamos la plaza?", listOf("Rompemos los juegos", "Usamos el basurero", "Pisamos las flores"), 1, "Usar el basurero mantiene limpio un espacio compartido.")
        )
    )
    else -> AprendizajeDemo(
        "Unidad 1: Descubrimos las vocales",
        "Reconocer las vocales A, E, I, O, U en palabras sencillas.",
        "Las vocales son A, E, I, O y U. Podemos encontrarlas al principio y dentro de muchas palabras.",
        "La palabra AVIÓN comienza con A. Di A lentamente y busca esa letra al inicio de la palabra.",
        "Mira las cinco vocales. Diles en voz alta y encuentra la A en AVIÓN.",
        "A   E   I   O   U\nA → AVIÓN",
        "Las cinco vocales y la letra A al comienzo de la palabra avión.",
        listOf(
            PreguntaDemo("¿Cuál de estas letras es una vocal?", listOf("M", "A", "P"), 1, "La A es una de las cinco vocales."),
            PreguntaDemo("¿Con qué vocal comienza AVIÓN?", listOf("E", "O", "A"), 2, "AVIÓN comienza con la vocal A."),
            PreguntaDemo("¿Cuál es el grupo de las cinco vocales?", listOf("A, E, I, O, U", "M, P, S, T, L", "B, C, D, F, G"), 0, "Las cinco vocales son A, E, I, O y U.")
        )
    )
}
