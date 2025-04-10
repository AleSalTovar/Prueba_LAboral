# Primer Paso

Crear la base de datos MySql con el archivo presente nombrado prueba_trabajo.sql

# Segundo Paso

Descargar los dos archivos nombrados manejo-estudiantes.war y ServerWebService.war

# Tercer Paso

Realizar el deploy de ambos archivos en su servidor de aplicaciones basado en Tomcat

# Cuarto Paso

cuando se realiza el deploy debes ingresar a las carpetas creadas en el servidor de aplicaciones (webapps), el paso es el mismo en ambas carpetas:

## Primer paso

Ingresar a las carpetas manejo-estudiante y ServerWebService

## Segundo paso

Ingresar la carpeta META-INF de cada una

## Tercer Paso

Abrir con su lector de texto predilecto el archivo context.xml de cada una de las aplicaciones

## Quinto Paso

En el el archivo debe modificar lo soguiente:
username="usuario para conectarse al servidor de base de datos"
password="contraseña del usuario de la base de datos"
url="reemplazar localhost:3306 por el nombre del servidor y el puerto"