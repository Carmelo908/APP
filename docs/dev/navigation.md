# Navigation

En la función AppNavigation se asocian todas las rutas a un pantalla. esta función es el punto de inicio de la aplicación.

En el archivo Routes.kt se define la clase `class Route(val route: String)` la cual representa una ruta de texto que la aplicación usará para navegar a la pantalla asociada. Esta adicionalmente tiene el método `fun resolve(arg: String): String` la cual se usa en rutas que siguen el formato `ruta/{arg}` en la que la función reemplaza lo que está a la derecha de la diagonal para pasar información por la ruta.

Todas las rutas están dentro del objeto AppScreens y son creadas con la clase Route.