# Como está hecho EduTrack

La aplicación utiliza el lenguaje Kotlin, el sistema de compilación Gradle y las librerías Jetpack Compose y Retrofit2, además fue desarrollada en el IDE Android Studio

En la ubicación *app/src/main/java/com/example/proyectoapp* se encuentra el código. El único archivo fuera de un paquete es MainActivity.kt, el cual solo abre la aplicación desde la navegación declarada dentro del paquete **navigation**

A continuación, se muestra una lista con todos los paquetes y subpaquetes de la aplicación, así como su propósito:

- **data**: contiene código relacionado con el manejo de datos de usuario y la interacción de la aplicación con el servidor. Está dividido en 3 subpaquetes:
  - **model**: clases de objetos de datos simples relacionados con los datos de usuario.
  - **network**: funciones desde las cuales se consume la API.
  - **storage**: funcionalidad relacionada con el almacenamiento temporal de la llave de la API en el dispositivo.
- **navigation**: contiene la funcionalidad de la navegación entre pantallas, la declaración de todas las rutas necesarias para esta navegación y se asocian todas las rutas a una pantalla dentro del paquete *screens*.
- **screens**: contiene funciones *@composable* que crean las distintas pantallas dentro del programa. cada pantalla contiene en su archivo la funcionalidad necesaria para las acciones que el usuario puede realizar en ellas-
- **ui**: Otros paquetes relacionados con la interfaz de usuario:
  - **components**: contiene algunos de los componentes usados en la aplicación, especialmente componentes complejos o que son usados más de una vez.
  - **layouts**: contiene distribuciones usadas en el programa en funciones *@composable* que construyen un *scaffold* y a las que se le debe poner un contenido.
  - **theme**: contiene código relacionado con el tema de color de la aplicación.
