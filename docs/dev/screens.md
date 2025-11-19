# Screens

Flujo de pantallas:

LoginScreen -> HomeScreen <-> CourseListScreen -¬
                                                \/
                                            SubjectsScreen?
                                                \/
InstanceScreen <-> InstanceListScreen <-> StudentListScreen <-> StudentScreen

(LoginScreen)[..\app\src\main\java\com\example\proyectoapp\screens\LoginScreen.kt]: la primera pantalla en ser mostrada. Contiene el formulario de inicio de sesión con los campos email y contraseña.
(HomeScreen)[..\app\src\main\java\com\example\proyectoapp\screens\LoginScreen.kt]: pantalla que contiene datos del usuario y tiene un botón para cerrar la sesión.
(CourseListScreen)[..\app\src\main\java\com\example\proyectoapp\screens\CourseListScreen.kt]: muestra la lista de cursos en la que cada item se puede tocar para ver sus materias (solo si el docente tiene más de una materia por curso).
(SubjectsScreen)[..\app\src\main\java\com\example\proyectoapp\screens\SubjectsScreen.kt]: permite seleccionar una asignatura dentro del curso. Solo aparece si el usuario tiene más de una asignatura en ese curso, en caso contrario la aplicación abrirá la pantalla StudentListScreen directamente en el único curso disponible.
(StudentListScreen)[..\app\src\main\java\com\example\proyectoapp\screens\StudentListScreen.kt]: muestra la lista de estudiantes del curso. Cada alumno se puede seleccionar para navegar a la StudentScreen.
(StudentScreen)[..\app\src\main\java\com\example\proyectoapp\screens\StudentScreen.kt]: Muestra las información de un estudiante en todas las instancias.
(IntanceListScreen)[..\app\src\main\java\com\example\proyectoapp\screens\InstanceListScreen.kt]: Muestra la lista de instancias de evaluación y permite la creación de nuevas instancias.
(InstanceListScreen)[..\app\src\main\java\com\example\proyectoapp\screens\InstanceScreen.kt]: Muestra todas las notas de los alumnos de un curso en esa instancia.