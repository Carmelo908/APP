# Introducción a EduTrack

Lo primero para utilizar la aplicación es tener un usuario, el cual debe ser solicitado a un administrador. si el servidor se encuentra funcionando y el dispositivo está conectado a internet, se deberán completar el **email** y la **contraseña** del usuario y darle al botón de iniciar sesión.

Una vez dentro se mostrará la pantalla de inicio y en la barra de navegación inferior se mostrará como segundo la pestaña de cursos, al seleccionarla se mostrarán los cursos guardados por el usuario y justo por encima de la barra inferior se mostrará un botón para añadir un curso. al presionar este botón se podrá añadir el curso con una asignatura asociada.

> **Nota:** Los cursos solo se pueden cargar desde los que están disponibles en el servidor. Si se requiere un curso faltante o alguno tiene información incorrecta, por favor, contacte a un administrador.

> **Advertencia:** En la versión actual, el botón no está implementado y no funciona. Se describe la visión de como sería su implementación una vez que esté completa. Tampoco está implementada la funcionalidad descrita a continuación.

Al cargar un curso, se carga la lista de alumnos y el usuario debe especificar la o las asignaturas que enseña a ese curso. Ahora el curso aparecerá en la lista de cursos y se podrá seleccionar pulsando encima. Debajo del nombre del curso dirá la asignatura si se ha especificado una sola o dirá "Varias asignaturas" si se han especificado más de una asignatura para ese curso específico. 

Al pulsar el curso, si el usuario ha especificado una sola asignatura se pasará directamente a una pantalla con 2 pestañas: alumnos e instancias. Si el usuario a especificado más de una asignatura para ese curso, se pasará primero a una pantalla donde tendrá que elegir la asignatura y al elegirla se mostrará la pantalla con las pestañas de alumnos e instancias en la barra inferior. 

En la pestaña de instancias, todo puede ser modificado por el usuario, así como se pueden añadir instancias nuevas. Para esto, se debe presionar el botón flotante sobre la barra inferior. los datos necesarios para esto son:

1. la fecha, que por defecto es el día actual pero puede especificarse a una fecha pasada o futura.
2. El tipo de instancia, el cual debe ser uno de los siguientes:
  - Tarea 
  - Asistencias
  - Proyecto / trabajo grupal
  - Participación en clases
  - Trabajo Práctico
  - Actividad aúlica
  - Otro (especificar)

Una vez creada la instancia, esta se podrá ver en la lista de instancias, la cual está ordenada por fecha. al presionar en la instancias, se podrán ver las notas de todos los alumnos en esa instancias, las cuales están deben de ser puestas por el usuario. También se pueden ver las notas de un alumno en todas las instancias de esa asignatura yendo a la lista de alumnos y seleccionando el alumno. 

Esta es toda la información necesaria para utilizar el programa.