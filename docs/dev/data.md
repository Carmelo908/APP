# Model

## Data

- Course: represente un curso y tiene asociadas las clases CoursePostResponse para cagar un curso y la clase CoursePostRequest con información para la solicitud
- EvaluationInstance: representa una instancia de evaluación
- LoginResponse y LoginResquest: se usan para solicitar y cargar información de inicio de sesión
- Student: representa un estudiante y tiene asociada la clase StudentsResponse para cargar estudiantes
- User: representa un usuario identificado con un correo electrónico y con una contraseña

## Network

Contiene la interfaz ApiService en la cual las funciones obtienen información desde la API y suelen requerir que se les pase información de solicitud y un token:

`suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>`: envía datos de inicio de sesión y devuelve un usuario si los datos son correctos o una solicitud fallida en caso contrario. No requiere token sinó que es esta la que devuelve el token en caso de que la petición sea exitosa.

`suspend fun getCourses(@Header("Authorization") token: String?): Response<List<Course>>`: devuelve la lista de cursos.

`@POST("logout") suspend fun logoutUser(@Header("Authorization") token: String?)`: cierra la sesión del usuario.

`@GET(value = "courses/{course_id}/students") suspend fun getStudentsByCourse(
        @Header(value = "Authorization") token: String?,
        @Path(value = "course_id") courseId: Int
    ): Response<List<Student>>`: 
devuelve la lista de alumnos de un curso

## Storage

Contiene la clase `UserPreferences` la cual gestiona el token de acceso con los métodos `suspend fun saveToken(token: String)` y `suspend fun clearToken()`.