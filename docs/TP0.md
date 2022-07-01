# TP0
El objetivo de esta primera entrega es configurar el entorno de trabajo de cada uno de los integrantes del grupo, además de probar las herramientas que utilizaremos durante el proyecto.
Para eso modelaremos en código la primera versión de algunas de las entidades principales.

## Usuarios
De los usuarios nos interesa:
1. Nombre: Su nombre real.
1. Apellido: Su apellido real.
1. Username: El alias que lo identificará dentro de la aplicación.
1. Fecha de Alta: es la Fecha en que comenzó a utilizar la aplicación.
1. Antigüedad: se determina a partir de la Fecha de Alta.
1. País de residencia.

## Destinos
De los destinos sabemos:
1. País
1. Ciudad.
1. Costo Base: cada destino tiene un costo base propio.

Queremos poder determinar si un destino es local, sabiendo que los destinos consideran a la Argentina como país de localía.

Costo,  que tiene un usuario por visitarlo, se calcula según:

Si el destino no es local se le debe sumar un 20% al costo base.
Si el Usuario que lo visita es del mismo país que el destino, se le aplica un descuento del 1% del costo base por cada año de antigüedad, contemplando un máximo de 15 años

	Ejemplo: Destino : Italia , costo base: $ 30.000 
Usuario residente en Italia, usando aplicación 16 años
Costo = 30.000 + 6.000 - 4.500 = $ 31.500

## Se pide:
Diseñar e implementar el modelo de objetos de dominio.

Subir el proyecto al repositorio del grupo en GitHub.

Cada integrante del grupo deberá:

Crear un nuevo branch
Desarrollar al menos un test.
Pushear su/s test/s a su branch
Generar un Pull Request de su branch a main
Revisar el Pull Request de alguno de sus compañeros (no mergear)

Una vez que todos los branches sean mergeados (previa revisión del tutor del grupo), generar un tag llamado “entrega-0” en el repositorio.
