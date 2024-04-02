package es.ilerna.vt3_uf1_verfuturo

import java.util.Calendar

//Clase oraculo con parametros dia y mes
class Oraculo(val day: Int, month: Int) {

    //Lista aarayOf elementos parcialmente inmutables (Signos del zodiaco)
    private val zodiacSigns = arrayOf(
        "aries", "tauro", "geminis", "cancer", "leo", "virgo",
        "libra", "escorpio", "sagitario", "capricornio", "acuario", "piscis"
    )

    //Atributo acceder desde fuera signo zodiaco--->funcion que recibe dia y mes
    val sign = getZodiacSign(day, month)


    // Arrays por Signos del Zodiaco
    private var ariesFutures = arrayOf(
        "Domingo: Aries, una noticia sorpresa llegará a ti.",
        "Lunes: Aries, el inicio de semana trae nuevas oportunidades.",
        "Martes: Un encuentro inesperado cambiará tu perspectiva, Aries.",
        "Miércoles: Aries, es un buen día para enfocarte en tus finanzas.",
        "Jueves: La salud debe ser tu prioridad hoy, Aries.",
        "Viernes: Aries, la diversión y la aventura te esperan en la noche.",
        "Sábado: Un día tranquilo y pacífico para Aries."
    )
    private var taurusFutures = arrayOf(
        "Domingo: Un día de descanso y reflexión para Tauro.",
        "Lunes: Tauro, enfrentarás desafíos que requerirán tu paciencia.",
        "Martes: Un día favorable para asuntos amorosos, Tauro.",
        "Miércoles: Tauro, cuidado con las decisiones impulsivas hoy.",
        "Jueves: Un viejo amigo te buscará, Tauro.",
        "Viernes: Tauro, es un buen día para relajarte y cuidarte.",
        "Sábado: La familia será importante para Tauro hoy."
    )
    private var geminisFutures = arrayOf(
        "Domingo: Géminis, la reflexión te traerá claridad.",
        "Lunes: Géminis, la comunicación será clave hoy.",
        "Martes: Un pequeño viaje podría estar en tu futuro, Géminis.",
        "Miércoles: Géminis, cuidado con los malentendidos hoy.",
        "Jueves: Un día para explorar nuevas oportunidades, Géminis.",
        "Viernes: Géminis, la noche trae encuentros emocionantes.",
        "Sábado: Un día para relajarte y disfrutar, Géminis."
    )
    private var cancerFutures = arrayOf(
        "Domingo: Cáncer, la reflexión te brindará paz.",
        "Lunes: Cáncer, un nuevo proyecto te emocionará.",
        "Martes: La empatía te abrirá puertas, Cáncer.",
        "Miércoles: Cáncer, un desafío te permitirá crecer.",
        "Jueves: Un día para cuidarte y mimarte, Cáncer.",
        "Viernes: Cáncer, la diversión te espera en cada esquina.",
        "Sábado: Un día para conectar con tus seres queridos, Cáncer."
    )

    private var leoFutures = arrayOf(
        "Domingo: Leo, un mensaje importante llegará a ti.",
        "Lunes: Leo, tu liderazgo será crucial hoy.",
        "Martes: Un día para explorar y aprender, Leo.",
        "Miércoles: Leo, cuidado con las tensiones en el trabajo.",
        "Jueves: Un día para celebrar pequeños logros, Leo.",
        "Viernes: Leo, la aventura te llama.",
        "Sábado: Un día para descansar y rejuvenecer, Leo."
    )
    private var virgoFutures = arrayOf(
        "Domingo: Virgo, la planificación del futuro te beneficiará.",
        "Lunes: Virgo, la organización será tu mejor amiga.",
        "Martes: Un día para cuidar de tu bienestar, Virgo.",
        "Miércoles: Virgo, un encuentro casual será significativo.",
        "Jueves: Un día para enfocarte en tus pasiones, Virgo.",
        "Viernes: Virgo, la noche trae sorpresas emocionantes.",
        "Sábado: Un día para disfrutar de la naturaleza, Virgo."
    )
    private var libraFutures = arrayOf(
        "Domingo: Libra, la reflexión te brindará respuestas.",
        "Lunes: Libra, busca el equilibrio en tus decisiones.",
        "Martes: La armonía en las relaciones será vital hoy, Libra.",
        "Miércoles: Libra, un viejo amigo te traerá noticias.",
        "Jueves: Un día para explorar tu creatividad, Libra.",
        "Viernes: Libra, la noche te reserva una sorpresa especial.",
        "Sábado: Un día para relajarte y disfrutar de la paz, Libra."
    )
    private var scorpioFutures = arrayOf(
        "Domingo: Escorpio, la meditación te traerá serenidad.",
        "Lunes: Escorpio, enfrentarás un desafío emocional.",
        "Martes: Un día para explorar lo desconocido, Escorpio.",
        "Miércoles: Escorpio, un secreto será revelado.",
        "Jueves: Un día para conectarte con tu yo interior, Escorpio.",
        "Viernes: Escorpio, la pasión y el romance están en el aire.",
        "Sábado: Un día para aventurarte fuera de tu zona de confort, Escorpio."
    )
    private var sagittariusFutures = arrayOf(
        "Domingo: Sagitario, la reflexión te brindará nuevas perspectivas.",
        "Lunes: Sagitario, un viaje está en tu horizonte.",
        "Martes: La verdad será tu guía hoy, Sagitario.",
        "Miércoles: Sagitario, un desafío te espera.",
        "Jueves: Un día para aprender algo nuevo, Sagitario.",
        "Viernes: Sagitario, la diversión te espera en cada esquina.",
        "Sábado: Un día para explorar y aventurarte, Sagitario."
    )

    private var capricornFutures = arrayOf(
        "Domingo: Capricornio, la reflexión te brindará claridad.",
        "Lunes: Capricornio, tu determinación te llevará lejos.",
        "Martes: Un día para planificar el futuro, Capricornio.",
        "Miércoles: Capricornio, un obstáculo requerirá tu atención.",
        "Jueves: Un día para enfocarte en tus metas, Capricornio.",
        "Viernes: Capricornio, relájate y disfruta de las pequeñas cosas.",
        "Sábado: Un día para conectar con la naturaleza, Capricornio."
    )

    private var aquariusFutures = arrayOf(
        "Domingo: Acuario, la reflexión te abrirá nuevas puertas.",
        "Lunes: Acuario, tu creatividad brillará hoy.",
        "Martes: Un día para innovar y soñar, Acuario.",
        "Miércoles: Acuario, un amigo necesitará tu apoyo.",
        "Jueves: Un día para explorar nuevas ideas, Acuario.",
        "Viernes: Acuario, la noche te traerá inspiración.",
        "Sábado: Un día para disfrutar de la libertad y la aventura, Acuario."
    )
    private var piscesFutures = arrayOf(
        "Domingo: Piscis, la meditación te conectará con tu yo superior.",
        "Lunes: Piscis, tu intuición te guiará.",
        "Martes: Un día para cuidar de ti y de los demás, Piscis.",
        "Miércoles: Piscis, un sueño te traerá un mensaje importante.",
        "Jueves: Un día para explorar tu lado espiritual, Piscis.",
        "Viernes: Piscis, la noche te traerá magia y misterio.",
        "Sábado: Un día para relajarte cerca del agua, Piscis."
    )


    //Metodo obtener el signo zodiaco, ver array zodiacSigns ---> Devuelve String
    private fun getZodiacSign(day: Int, month: Int): String {

        var sign = ""
        when (month) {
            1 -> sign = if (day < 20) zodiacSigns.get(9) else zodiacSigns.get(10)
            2 -> sign = if (day < 19) zodiacSigns.get(10) else zodiacSigns.get(11)
            3-> sign = if (day < 21) zodiacSigns.get(11) else zodiacSigns.get(0)
            4 -> sign = if (day < 20) zodiacSigns.get(0) else zodiacSigns.get(1)
            5 -> sign = if (day < 21) zodiacSigns.get(1) else zodiacSigns.get(2)
            6 -> sign = if (day < 21) zodiacSigns.get(2) else zodiacSigns.get(3)
            7 -> sign = if (day < 23) zodiacSigns.get(3) else zodiacSigns.get(4)
            8 -> sign = if (day < 23) zodiacSigns.get(4) else zodiacSigns.get(5)
            9 -> sign = if (day < 23) zodiacSigns.get(5) else zodiacSigns.get(6)
            10 -> sign = if (day < 23) zodiacSigns.get(6) else zodiacSigns.get(7)
            11 -> sign = if (day < 22) zodiacSigns.get(7) else zodiacSigns.get(8)
            12 -> sign = if (day < 22) zodiacSigns.get(8) else zodiacSigns.get(9)
        }
        return sign
    }

    //Metodo obtener dia semana actual: Domingo es 1, lunes 2...   return un Int
    private fun getDayWeek(): Int {
        val calendar = Calendar.getInstance()
        val dayWeek = calendar.get(Calendar.DAY_OF_WEEK)

        return dayWeek
    }

    //Metodo obtener posicion en el array del signo del zodiaco
    private fun getZodiacSignPosition(): Int {
        return zodiacSigns.indexOf(sign)
    }

    //Funcion OBTENER PREDICCION DEL ORACULO --> recibe el nombre y return prediccion
    fun getPrediction(nombre: String?): String {

        var prediction: String = "Hola $nombre "
        val numDayWeek = getDayWeek()-1  //Ojo restar 1 por la idexacion basada en 0

        when(sign){
            "aries" -> prediction = "$prediction\n${ariesFutures[numDayWeek]}"
            "tauro" -> prediction = "$prediction\n${taurusFutures.get(numDayWeek)}"
            "geminis" -> prediction = "$prediction\n${geminisFutures.get(numDayWeek)}"
            "cancer" -> prediction = "$prediction\n${cancerFutures.get(numDayWeek)}"
            "leo" -> prediction = "$prediction\n${leoFutures.get(numDayWeek)}"
            "virgo" -> prediction = "$prediction\n${virgoFutures.get(numDayWeek)}"
            "libra" -> prediction = "$prediction\n${libraFutures.get(numDayWeek)}"
            "escorpio" -> prediction = "$prediction\n${scorpioFutures.get(numDayWeek)}"
            "sagitario" -> prediction = "$prediction\n${sagittariusFutures.get(numDayWeek)}"
            "capricornio" -> prediction = "$prediction\n${capricornFutures.get(numDayWeek)}"
            "acuario" -> prediction = "$prediction\n${aquariusFutures.get(numDayWeek)}"
            "piscis" -> prediction = "$prediction\n${piscesFutures.get(numDayWeek)}"


        }

        return prediction
    }

}