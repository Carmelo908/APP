# EduTrack

Trabajo práctico de tercer trimestre de la asignatura de Programación II, 6to año de secundaria en la tecnicatura de computación

La aplicación EduTrack es un sistema de anotación especialmente hecho para que los profesores puedan anotar de forma ordenada las instancias de evaluación de sus alumnos, así como las notas en un sistema digital cuyos datos son guardados en un servidor mediante una API web. Este servidor también cuenta con un sistema de usuarios e inicio de sesión. La aplicación está pensada como un reemplazo más práctico de las anotaciones físicas en papel.

Estos sistemas están implementados en una API web desarrollada por mi compañero de equipo Fernando, y cuyo código está [en este repositorio](https://github.com/Ferchupessoadev/api-asistente-de-notas) y que se encuentra funcionando en un servidor montado en su hogar al cual se conecta la aplicación.

La aplicación está desarrollada para android nativo con el IDE Android Studio, el lenguaje de programación Kotlin y la librería Jetpack Compose para la interfaz gráfica.

## Características

- Sistema de usuarios cuyos datos se guardan en la nube
- Capacidad de anotar instancias de evaluación indicando el tipo (por ejemplo: tarea, actividad aúlica, etc)
- Cursos añadidos desde la API para que el docente pueda seleccionarlos y anotar la asignatura e instancias
- Capacidad de filtrar las notas de los alumnos en un curso por instancias de evaluación o filtrar las notas de un alumno en todas las instancias
- Modificación de información
- Interfaz simple y clara

**Para más información, vaya la carpeta *./docs***