# TP3

Además de permitir, a los usuarios, compartir sus itinerarios de viajes, queremos que puedan estimar y comparar distintas propuestas, a la hora de elegir dónde viajar.

Luego de configurar un viaje, y ver si el costo del mismo es un valor acorde a su presupuesto, el usuario confirma realizar el viaje. Esto debe actualizar los destinos visitados por él.
A este comportamiento habitual queremos brindarle la posibilidad al usuario que active o desactive otros de forma dinámica.
Al momento tenemos para ofrecer los siguientes:

Enviar a los amigos que desean el destino del viaje un mail avisando que visitaron el destino. El mail se envía desde app@holamundo.com , el asunto “Visitaron un destino que te puede interesar” y el cuerpo del mail “ Hola! <nombre usuario receptor>, <nombre y apellido usuario emisor> visitó <País y Ciudad destino>.
Si el viaje no es local, modificar el criterio de selección de itinerario a localista, esto le asegura al usuario que la app va a proponer destinos locales para cuando quiera  armar un nuevo viaje).
Agregar a los itinerarios a puntuar el itinerario del viaje.
Si el viaje no tiene un vehículo con convenio, el usuario se convierte en selectivo, y le pasan a gustar los vehículos de una marca con convenio.

## Tareas
Para ahorrarle “trabajo” a los usuarios, le ofrecemos algunas tareas  que puede preestablecer, para luego ejecutarlas en el momento que lo desee.

* Darle un puntaje a los itinerarios a puntuar. Para eso se debe especificar el puntaje que queremos darle a todos los itinerarios.
* Transferir todos sus itinerarios al amigo que menos destinos visitados tenga.
* Hacerse amigo de todos los usuarios que conozcan un destino específico.
* Agregar a los destinos deseados, el destino deseado más caro de cada uno de sus amigos (el que el amigo considere más caro para sí mismo).

Todas las tareas deben enviar un mail al usuario, indicando:  “Se realizó la tarea: < nombre de tarea >  en el asunto y cuerpo.
La solución dada, debe contemplar que en próximas iteraciones aparezcan nuevas tareas.


## Se pide:


Diseñar e implementar el modelo de objetos de dominio.

* Diseñar e implementar los casos de prueba correspondientes.
* Armar el juego de datos necesario para realizar las pruebas.
* Codificar los tests unitarios.
* Usar mocks y/o stubs para testear los casos que impliquen comunicación a una interfaz de salida.

La entrega debe desarrollarse en un branch develop, que será mergeado en master, a través de un pull request, luego de ser aprobado por el tutor del grupo.


Generar un tag llamado “entrega-3” en el repositorio.
