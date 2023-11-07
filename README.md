# Título: BiblioControl - Automatización de la Gestión de Bibliotecas
## Introducción
La aplicación BiblioControl se ha creado con el propósito de simplificar y automatizar la gestión de bibliotecas, llegando incluso a suplir la necesidad de un bibliotecario. Esta aplicación ofrece varias funcionalidades esenciales que permiten a los administradores y usuarios de la biblioteca llevar a cabo sus tareas de manera eficiente.

<p align="center">
<img width="654" alt="Esquema Inicio" src="https://github.com/ac-14/TrabajoJava/assets/119895282/a47e674a-dcc3-441a-beef-1ae9a2af8f91">
</p>

## Funcionalidades Principales
Inicio de Sesión y Administración de Usuarios: La aplicación presenta una ventana principal con tres opciones: Iniciar sesión, crear usuario y recuperar acceso en caso de olvido de la contraseña. Para crear un usuario se necesitará un DNI y una contraseña. Al iniciar sesión, se distinguen dos tipos de usuarios: el administrador y los usuarios de la biblioteca.
### Administador
El administrador tiene acceso a un conjunto de herramientas para gestionar la biblioteca:<br />
•	Gestión de Libros: Puede añadir, eliminar y reubicar libros en la biblioteca.<br />
•	Configuración: Accede a una pestaña de configuración para ajustar parámetros.<br />
•	Gestión de Usuarios: Puede bloquear el acceso de ciertos usuarios por razones específicas, cambiar contraseñas, etc.<br />
•	Gestión de Peticiones: Visualiza las solicitudes de libros de los usuarios, permitiendo la compra y adición posterior de los mismos.<br />
<p align="center">
<img width="215" alt="Menu_Administrador" src="https://github.com/ac-14/TrabajoJava/assets/119895282/399a9441-6d0f-4387-a260-e001fa61ac0b">
</p>

<p align="center">
<img width="246" alt="Busqueda_Usuario" src="https://github.com/ac-14/TrabajoJava/assets/119895282/b3696718-cc96-4288-9d97-4bb41894b07a"><img width="197" alt="Resultado_Usuario" src="https://github.com/ac-14/TrabajoJava/assets/119895282/526ca044-9c23-497a-a1d2-d787259a670b">
</p>

### Usuario
Los usuarios de la biblioteca, al iniciar sesión con su DNI y contraseña, pueden realizar las siguientes acciones:<br />
•	Comprobar Disponibilidad: Verificar la disponibilidad de libros en la biblioteca.<br />
•	Reservar Libros: Reservar libros que estén disponibles.<br />
•	Solicitar Libros: Pedir libros que no estén en la biblioteca.<br />
•	Recuperar Contraseña: En caso de olvidar la contraseña, le llegará la solicitud al administrador para reestablecer el acceso al usuario.<br />
•	Recomendación de Libro: Si el usuario quiere disfrutar un libro aleatorio, podrá el programa le sugerirá uno.<br />

<p align="center">
<img width="220" alt="Menu_Usuario" src="https://github.com/ac-14/TrabajoJava/assets/119895282/615e38d2-ad12-4ce5-a2af-a87c14206158">
</p>

### Conexión con base de datos
Además, la aplicación se conecta a una base de datos de libros, permitiendo la búsqueda de información a través del ISBN y la descarga automática de datos como el título, la portada y el género.

<p align="center">
<img width="190" alt="Busqueda_Libro" src="https://github.com/ac-14/TrabajoJava/assets/119895282/7783bc5a-1f60-4faf-b443-2958e1a27119"><img width="153" alt="Resultado_Libro" src="https://github.com/ac-14/TrabajoJava/assets/119895282/dc0fc92a-9750-4718-89bd-0074c5a6051a">
</p>

### Funcionalidad adicional: Control de ruido
Además, nuestra aplicación incluye una función que, utilizando el micrófono del ordenador la aplicación mide los niveles de ruido. Si los decibelios superan un umbral establecido por el administrador, la aplicación emitirá un mensaje de advertencia en voz alta, solicitando silencio en la biblioteca. Además, el administrador recibirá una notificación si se ha excedido el límite de ruido.
<p align="center">
<img width="217" alt="imagen" src="https://github.com/ac-14/TrabajoJava/assets/119895282/21cbb7ff-d482-4826-b84b-0222d2be427b">
</p>

### Diagrama UML
<p align="center">
<img width="882" alt="imagen" src="https://github.com/ac-14/TrabajoJava/assets/119895282/0dcdc540-0513-48a3-b900-6c2922718e67">
</p>

### Práctica 2
Hemos agregado las clases principales del programa. Hemos hecho los menus de todas las clases y agregado algunas funcionalidades. A continuación agregamos imagenes del funcionamiento.

Menú Usuario
<p align="center">
<img width="198" alt="Captura de pantalla 2023-10-30 a las 9 18 36" src="https://github.com/ac-14/TrabajoJava/assets/119895282/ac78a651-ca01-4400-82bb-62f0cb272eba"><img width="230" alt="Captura de pantalla 2023-10-30 a las 9 18 46" src="https://github.com/ac-14/TrabajoJava/assets/119895282/1c89d0a6-d28f-4339-b607-3d43889099aa">
</p>

Menú Admin
<p align="center">
<img width="214" alt="Captura de pantalla 2023-10-30 a las 9 23 39" src="https://github.com/ac-14/TrabajoJava/assets/119895282/594892ce-a126-47bd-adf9-eb9a903ae613"><img width="198" alt="Captura de pantalla 2023-10-30 a las 9 23 50" src="https://github.com/ac-14/TrabajoJava/assets/119895282/ee21356b-d572-48e2-befb-17583cf0ea1f">
</p>

Reserva libro
<p align="center">
<img width="391" alt="Captura de pantalla 2023-10-30 a las 9 22 35" src="https://github.com/ac-14/TrabajoJava/assets/119895282/3718993d-c7e5-488a-977d-4268339f0435">
</p>

Añadir libro
<p align="center">
<img width="234" alt="Captura de pantalla 2023-10-30 a las 9 30 26" src="https://github.com/ac-14/TrabajoJava/assets/119895282/f27dd1a9-71e4-4a2d-b877-0113266c4708"><img width="386" alt="Captura de pantalla 2023-10-30 a las 9 32 02" src="https://github.com/ac-14/TrabajoJava/assets/119895282/1cff6f67-42b7-4946-a786-9c044c2ffba4">
