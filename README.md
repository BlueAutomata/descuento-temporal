This is a Kotlin Multiplatform project targeting Android, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

## Descripción
1.	Tarea de descuento temporal
2.	Esta tarea fue utilizada en un proyecto investigativo que buscaba comprender factores comportamentales en pacientes con diagnóstico de diabetes tipo 2 y prediabetes, la tarea permitió el analizar si existía alguna tendencia de los participantes a desvalorizar las recompensas a medida que el tiempo para su obtención aumentaba, esta tarea utilizo una secuencia fija en orden descendente es decir que las recompensas iban disminuyendo a medida de cada ensayo.
3.	La aplicación permitió el registrar la elección de los participantes mediante la presentación de dos tipos de recompensas: una inmediata con menor valor frente a una demorada, pero con mayor valor, las demoras fueron presentadas en 6 bloques diferentes, cada bloque consistía en 20 recompensas inmediatas diferentes mientras que el valor de la demorada permanecía constante. 
Las recompensas mencionadas anteriormente fueron: 1 semana, 1 mes, 3 meses, 6 meses, 1 año y 6 años. 

La tarea de descuento presentaba los siguientes apartados:
![image](https://github.com/user-attachments/assets/fb79fa45-958b-4bf4-9530-58716f1fde1d)

Primero permitía seleccionar la carpeta donde se guardaría los datos registrados.
![image](https://github.com/user-attachments/assets/f6773281-c9c0-42d5-b99e-d50937951713)

Posteriormente se realizaba el registro de los datos del participante, en el documento solo se permitía la digitación de valores numéricos.

![image](https://github.com/user-attachments/assets/2299d479-a8cd-4875-97a9-fc2b8902c2ce)

Luego se continuó con la presentación de las instrucciones generales a los participantes, para continuar debía presionar el botón siguiente. 

![image](https://github.com/user-attachments/assets/1e66ee57-6ad3-4eb0-8c7b-ecd5c56da686)

Al continuar se le indicaba al participante la presentación de ejemplos y se realizaba la solicitud de su atención en cada ensayo.

![image](https://github.com/user-attachments/assets/ad1d1eb4-3151-4e71-961c-77a8f667be37)

En el primer ejemplo la indicación era realizar la selección de la recompensa inmediata, si el participante seleccionaba la recompensa demorada no se habilitaba el botón de siguiente como se evidencia en la imagen presentada anteriormente. 

![image](https://github.com/user-attachments/assets/9aeb4bb4-d5ff-4af3-9712-726a66e53417)

En el segundo ejemplo se presentaba la instrucción de seleccionar la recompensa demorada, si esta era seleccionada el botón de siguiente era habilitado como se evidencia en la imagen anterior. 

![image](https://github.com/user-attachments/assets/d20b85ac-195e-4313-8aa6-798d952fa25e)

Cuando se seleccionaban de manera correcta los ejemplos, se direccionaba al participante al siguiente apartado donde se le presentaba el tiempo de la recompensa demorada. 

![image](https://github.com/user-attachments/assets/37e64cd2-7120-4bc2-807c-2ef97e9fceb2)

Al dar siguiente se comenzaba con la tare de descuento en el cual el participante realizaba su elección. 

![image](https://github.com/user-attachments/assets/f1cc70a4-2cc8-489a-b8f3-46122819b859)

El botón seleccionado por el participante se presentaba con un color más oscuro y se habilitaba la opción de siguiente para ser dirigido a otro ensayo, al finalizar cada bloque se continuaba con la siguiente demora, A l finalizar se le agradecía al participante por su participación y se le indicaba que ya se podía cerrar el programa.

## Personas que contribuyeron

En la realización de la tarea de descuento contribuyeron:
- **Guillermo Luigui Ubaldo Nieto Angarita** quien realizó el diseño y programación de la tarea, como también brindo asesoramiento de su descarga, uso y búsqueda de datos.
- **Heidy Lorena Merchan Moya** quien brindo instrucciones frente a la realización de la tarea de descuento.
- **Carol Tatiana Sierra Quintana** quien realizó diferentes pilotajes de la tarea brindando sugerencias que permitieran llegar al producto final.

## Pasos para correr el programa
1. Clonar el repositorio con git clone
2. Abrir el proyecto con Android Studio o Intellij IDEA
3. Abrir terminar
4. Ejecutar `./gradlew packageMsi` para crear el instalador
5. Ejecutar el archivo .msi para instalar el programa
