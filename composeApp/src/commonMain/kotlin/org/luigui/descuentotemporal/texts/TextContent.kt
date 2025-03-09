package org.luigui.descuentotemporal.texts

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

// TextContent.kt
object TextContent {
    const val consentHeading = "Decisiones de Salud y Descuento Temporal en Pacientes con Pre diabetes y Diabetes Tipo 2"
    const val consentDataProtectionHeading = "Política de protección de datos"
    const val consentDataProtection = "La información recopilada en este estudio es estrictamente confidencial y se usará solo con fines investigativos. Se generará un código aleatorio para cada participante para garantizar el anonimato. Los datos serán almacenados en plataformas digitales seguras de la Fundación Universitaria Konrad Lorenz, con acceso restringido y autenticación de doble factor. No habrá repositorios físicos de datos. Los datos anónimos se conservarán durante un máximo de 5 años para posibles auditorias éticas."
    const val consentIntroductionHeading = "Introducción"
    const val consentIntroduction = "Usted ha sido invitado(a) a participar en el estudio titulado \"Impacto del Descuento por Demora en la Adherencia al Tratamiento en Pacientes con Prediabetes y Diabetes Tipo 2\". Esta investigación de carácter formativo y académico es conducida por la estudiante Carol Tatiana Sierra Quintana de la asignatura electiva investigativa 2025-1 de la Fundación Universitaria Konrad Lorenz, el proceso de investigación formativa es monitoreada y guiada por Heidy Lorena Merchan Moya del Programa de Psicología  de la Fundación Universitaria Konrad Lorenz."
    const val consentStudyPurposeHeading = "Propósito Del Estudio"
    const val consentStudyPurpose = "El objetivo de este estudio es analizar las diferencias del descuento temporal en la toma de decisiones relacionadas con la adherencia al tratamiento en pacientes con diabetes tipo 2 (DM2) o prediabetes."
    const val consentDescriptionOfTheProcedureHeading = "Descripción Del Procedimiento"
    val consentDescriptionOfTheProcedure = """
        Es un estudio de tipo correlacional y su procedimiento comprende los siguientes pasos:
        1. Aplicación de cuestionario para obtener información sociodemografica.
        2. Aplicación de prueba  ASSIST para obtener información respecto al consumo de sustancias.
        3. Aplicación de prueba  SDSCA para obtener información respecto a la adherencia del tratamiento.
        4. Aplicación de una prueba cuantitativa la cual cuenta con 120 preguntas aproximadamente divididas en 6 bloques para una mejor compresión respecto a la toma de decisiones.
    """.trimIndent()
    const val consentBenefitsHeadings = "Retribución Y Beneficios Por La Participación"
    const val consentBenefits = "Por la participación en este estudio no existe ningún tipo de incentivo monetario, por tratarse de una investigación netamente académica. Su colaboración será de gran ayuda para el desarrollo académico de la estudiante que se encuentra cursando la materia. Especificar el incentivo."

    val consentRisksHeading = "Riesgos E Incomodidades"
    val consentRisks = """
        Tenga en cuenta que en este estudio no se aplican procedimientos que supongan daño o riesgo para usted o para otros.
        Para respetar el ejercicio de autonomía, confidencialidad, y bienestar de cada se hace mención de la Ley 1090 de 2006 como base transversal en el ejercicio de la investigación. 
        Se informa el procedimiento, su propósito, retribución de la investigación y confidencialidad de los datos.
    """.trimIndent()
    const val consentConfidentialityHeading = "Confidencialidad Y Anonimidad"
    const val consentConfidentiality = "Entiendo que mis respuestas acá consignadas son anónimas y no se me podrá identificar de manera directa en relación con estas. Cualquier información personal que haga parte de los resultados de la investigación será mantenida de manera confidencia y solo será utilizada para fines académicos en el marco del curso. Los datos podrán ser utilizados en publicaciones académicos, pero entiendo que en ninguna publicación en la que se usen mis resultados se mencionará mi nombre a menos que lo consienta y autorice por escrito."
    const val consentVoluntaryParticipantionHeading = "Participación Voluntaria"
    const val consentVoluntaryParticipantion = "La participación en este estudio es voluntaria. Entiendo que tengo la libertad de retirar mi consentimiento de participación en esta investigación en cualquier momento y que en tal caso no tendré ningún tipo de repercusión."
    const val consentInformationHeading = "Información"
    const val consentInformation = """
        Para obtener información acerca de esta investigación, puedo comunicarme con la estudiante Carol Tatiana Sierra Quintana a través del siguiente correo institucional carolt.sierraq@konradlorenz.edu.co o con la docente  Heidy Lorena Merchan Moya a través del correo heidy.merchanm@konradlorenz.edu.co

        Confirmo que he leído y acepto participar en el estudio
    """

    const val InstructionsHeading = "Instrucciones Generales (Descuento temporal)" // This is a compile-time constant
    const val NextButtonText = "Siguente" // This is a compile-time constant

    private val RawInstructions = """
        A continuación, responderás a una serie de elecciones para ganar recompensas monetarias. No existen respuestas correctas o incorrectas, ni hay un tiempo límite para responder. Las recompensas no las recibirás durante las elecciones ni al final de la sesión, pero te pedimos que respondas como si fueras a ganarlas. Las recompensas no son acumulables a lo largo de las alternativas; cada recompensa es independiente una de la otra. Elige la opción que tú prefieras y no la que otra persona elegiría. Responde conforme a tus preferencias el día de HOY. Evita responder en función del pasado o futuro. 

        Las opciones del lado izquierdo despliegan las recompensas que puedes ganar HOY, mientras que las opciones del lado derecho despliegan las recompensas que puedes ganar después de una demora específica.

        Para hacer tus elecciones: da clic en la opción que corresponda con tu preferencia.
    """.trimIndent()

    private val styledPhrases = listOf(
        "Elige la opción que tú prefieras y no la que otra persona elegiría" to SpanStyle(fontWeight = FontWeight.Bold),
        "a tus preferencias el día de HOY" to SpanStyle(fontWeight = FontWeight.Bold),
        "parte superior" to SpanStyle(fontWeight = FontWeight.Bold),
        "recompensas que puedes ganar HOY" to SpanStyle(fontWeight = FontWeight.Bold),
        "las recompensas que puedes ganar" to SpanStyle(fontWeight = FontWeight.Bold),
        "después de una demora específica" to SpanStyle(
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
    )

    // Function to create an AnnotatedString with styling for multiple phrases
    val Instructions: AnnotatedString
        get() = buildAnnotatedString {
            var text = RawInstructions

            // Iterate through each styled phrase and apply the corresponding style
            styledPhrases.forEach { (phrase, style) ->
                val startIndex = text.indexOf(phrase)
                if (startIndex != -1) {
                    // Append the text before the styled phrase
                    append(text.substring(0, startIndex))

                    // Apply the style to the phrase
                    withStyle(style = style) {
                        append(phrase)
                    }

                    // Update the remaining text
                    text = text.substring(startIndex + phrase.length)
                }
            }

            // Append any remaining text after the last styled phrase
            if (text.isNotEmpty()) {
                append(text)
            }
        }

    const val DiscountQuestion = "Elige una opción ¿Cuál prefieres?"

    val instructions = mapOf(
        1 to "Imagina que hoy debes elegir entre **GANAR** una **cantidad menor de dinero entregada AHORA** y otra **cantidad mayor de dinero entregada después de _1 SEMANA_**. La cantidad a ganar será totalmente segura y no habrá impedimentos para obtenerla. En la alternativa demorada, solo debes esperar el tiempo indicado para obtener la recompensa. Elige la opción de tu preferencia.\n\n**Responde cada elección en el orden en el que aparecen. No saltes elecciones.**",
        2 to "Imagina que hoy debes elegir entre **GANAR** una **cantidad menor de dinero entregada AHORA** y otra **cantidad mayor de dinero entregada después de _1 MES_**. La cantidad a ganar será totalmente segura y no habrá impedimentos para obtenerla. En la alternativa demorada, solo debes esperar el tiempo indicado para obtener la recompensa. Elige la opción de tu preferencia.\n\n**Responde cada elección en el orden en el que aparecen. No saltes elecciones.**",
        3 to "Imagina que hoy debes elegir entre **GANAR** una **cantidad menor de dinero entregada AHORA** y otra **cantidad mayor de dinero entregada después de _3 MESES_**. La cantidad a ganar será totalmente segura y no habrá impedimentos para obtenerla. En la alternativa demorada, solo debes esperar el tiempo indicado para obtener la recompensa. Elige la opción de tu preferencia.\n\n**Responde cada elección en el orden en el que aparecen. No saltes elecciones.**",
        4 to "Imagina que hoy debes elegir entre **GANAR** una **cantidad menor de dinero entregada AHORA** y otra **cantidad mayor de dinero entregada después de _6 MESES_**. La cantidad a ganar será totalmente segura y no habrá impedimentos para obtenerla. En la alternativa demorada, solo debes esperar el tiempo indicado para obtener la recompensa. Elige la opción de tu preferencia.\n\n**Responde cada elección en el orden en el que aparecen. No saltes elecciones.**",
        5 to "Imagina que hoy debes elegir entre **GANAR** una **cantidad menor de dinero entregada AHORA** y otra **cantidad mayor de dinero entregada después de _1 AÑO_**. La cantidad a ganar será totalmente segura y no habrá impedimentos para obtenerla. En la alternativa demorada, solo debes esperar el tiempo indicado para obtener la recompensa. Elige la opción de tu preferencia.\n\n**Responde cada elección en el orden en el que aparecen. No saltes elecciones.**",
        6 to "Imagina que hoy debes elegir entre **GANAR** una **cantidad menor de dinero entregada AHORA** y otra **cantidad mayor de dinero entregada después de _6 AÑOS_**. La cantidad a ganar será totalmente segura y no habrá impedimentos para obtenerla. En la alternativa demorada, solo debes esperar el tiempo indicado para obtener la recompensa. Elige la opción de tu preferencia.\n\n**Responde cada elección en el orden en el que aparecen. No saltes elecciones.**"
    )

    val startExperiment = "Comienza el experimento."
}